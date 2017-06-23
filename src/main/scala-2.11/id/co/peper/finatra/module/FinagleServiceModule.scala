package id.co.peper.finatra.module

import java.net.URL

import com.google.inject.{Provides, Singleton}
import com.twitter.conversions.time.intToTimeableNumber
import com.twitter.finagle.Http
import com.twitter.inject.TwitterModule
import com.typesafe.config.Config

object FinagleServiceModule extends TwitterModule {

  private def buildClient(config: Config) = {
    Http.client
      .withRequestTimeout(config.getInt("timeout") seconds)
      .withSessionPool.minSize(config.getInt("minConn"))
      .withSessionPool.maxSize(config.getInt("maxConn"))
      .withSessionPool.maxWaiters(config.getInt("maxConnWait"))

  }

  @Singleton
  @Provides
  def provideGiphyService(config: Config) = {
    buildClient(config.getConfig("client.http")).newService("151.101.42.2:80", "giphy")
  }

}
