package id.co.peper.finatra.service.impl

import com.github.racc.tscg.TypesafeConfig
import com.google.inject.Inject
import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import com.twitter.inject.Logging
import com.twitter.util.Future
import id.co.peper.finatra.model._
import id.co.peper.finatra.service.GiphyService
import id.co.peper.finatra.util.JsonUtils

class GiphyServiceImpl @Inject()(giphy: Service[Request, Response],
                                 @TypesafeConfig("giphy.api-key") apiKey: String)
  extends GiphyService with Logging {

  override def search(request: GiphySearchRequest): Future[GiphyResponse] = {
    giphy(buildSearchRequest(request))
      .map(response =>
        response.statusCode match {
          case 200 =>
            val responseObject = JsonUtils.fromJsonSnakeCase[GiphyApiSearchResponse](response.getContentString())
            val datas = if (request.showNsfw.get) responseObject.data else responseObject.data.filterNot(_.rating == "r")
            val images = datas.map(data =>
              GiphyImage(
                data.id, data.url, data.images.original.map(_.url), data.images.fixedHeight.map(_.url),
                data.images.fixedWidth.map(_.url), data.rating
              )
            )
            val page = responseObject.pagination
            GiphyResponse(page.offset, page.count, page.totalCount, images)
          case 429 =>
            warn(s"Too many request !!!")
            throw CommonException(429, "Too many request is being processed, please try again later.", null)
          case 404 =>
            warn(s"Request $request returning 404 - ${response.getContentString()}")
            throw CommonException(404, "The resource you are requesting not exists in GIPHY.", null)
          case _ =>
            error(s"Error error when requesting to GIPHY. ${response.getStatusCode()} - ${response.getContentString()}")
            throw CommonException(500, "Getting invalid reponse. Contact developer.", null)
        })
      .rescue {
        case e: CommonException => throw e
        case t: Throwable => error("Encountered exception when requesting to GIPHY.", t)
          throw new CommonException(500, "Internal server error.", t)
      }
  }

  private def buildSearchRequest(request: GiphySearchRequest) = {
    val params = Seq(
      "api_key" -> apiKey,
      "offset" -> request.offset.get.toString,
      "limit" -> request.limit.get.toString,
      "q" -> request.query
    )
    val req = Request("/v1/gifs/search", params: _*)
    req.host = "api.giphy.com"
    req
  }

}
