package id.co.peper.finatra.controller

import com.google.inject.Inject
import com.twitter.finatra.http.Controller
import id.co.peper.finatra.model.GiphySearchRequest
import id.co.peper.finatra.service.GiphyService

class GiphyController @Inject()(giphyService: GiphyService) extends Controller {

  get("/giphy/search") { req: GiphySearchRequest =>
    giphyService.search(req)
  }

}
