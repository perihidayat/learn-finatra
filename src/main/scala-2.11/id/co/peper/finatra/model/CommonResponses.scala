package id.co.peper.finatra.model

case class CommonException(status: Int, msg: String, cause: Throwable) extends Exception(msg, cause)

case class GiphyImage(id: String, giphyUrl: String, desktopUrl: Option[String], mobileUrlH: Option[String],
                      mobileUrlV: Option[String], rating: String)

case class GiphyResponse(offset: Int, found: Int, total: Int, images: Seq[GiphyImage])