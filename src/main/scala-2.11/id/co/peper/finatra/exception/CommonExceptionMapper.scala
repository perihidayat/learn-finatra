package id.co.peper.finatra.exception

import com.google.inject.Inject
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.inject.Logging
import id.co.peper.finatra.model.CommonException
import id.co.peper.finatra.util.JsonUtils

class CommonExceptionMapper @Inject()(response: ResponseBuilder)
  extends ExceptionMapper[CommonException] with Logging {

  override def toResponse(request: Request, throwable: CommonException): Response = {
    error("Caught exception.", throwable)
    response.status(throwable.status).body(JsonUtils.toJson(throwable))
  }
}
