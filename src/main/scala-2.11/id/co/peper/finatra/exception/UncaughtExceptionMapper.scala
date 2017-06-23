package id.co.peper.finatra.exception

import com.google.inject.Inject
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.inject.Logging
import id.co.peper.finatra.model.CommonException
import id.co.peper.finatra.util.JsonUtils

class UncaughtExceptionMapper @Inject()(response: ResponseBuilder)
  extends ExceptionMapper[Exception] with Logging {

  override def toResponse(request: Request, throwable: Exception): Response = {
    error("Error internal server.", throwable)
    val body = CommonException(500, "Internal server error.", throwable)
    response.status(500).body(JsonUtils.toJson(body))
  }
}
