package id.co.peper.finatra.server

import com.google.inject.Module
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, ExceptionMappingFilter, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter
import id.co.peper.finatra.controller.GiphyController
import id.co.peper.finatra.exception.{CommonExceptionMapper, UncaughtExceptionMapper}
import id.co.peper.finatra.module.{FinagleServiceModule, ConfigModule}

object APIServerMain extends APIServer

class APIServer extends HttpServer {

  protected override def failfastOnFlagsNotParsed: Boolean = true

  override protected def modules: Seq[Module] = Seq(
    ConfigModule,
    FinagleServiceModule
  )

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .filter[ExceptionMappingFilter[Request]]
      .add[GiphyController]
      .exceptionMapper[CommonExceptionMapper]
      .exceptionMapper[UncaughtExceptionMapper]
  }
}