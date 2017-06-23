package id.co.peper.finatra.service

import com.google.inject.ImplementedBy
import com.twitter.util.Future
import id.co.peper.finatra.model.{GiphyResponse, GiphySearchRequest}
import id.co.peper.finatra.service.impl.GiphyServiceImpl

@ImplementedBy(classOf[GiphyServiceImpl])
trait GiphyService {

  def search(request: GiphySearchRequest): Future[GiphyResponse]
}
