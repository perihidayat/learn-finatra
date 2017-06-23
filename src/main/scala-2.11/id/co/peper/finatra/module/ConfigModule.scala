package id.co.peper.finatra.module

import com.github.racc.tscg.TypesafeConfigModule
import com.google.inject.{Provides, Singleton}
import com.twitter.inject.{Logging, TwitterModule}
import com.typesafe.config.ConfigFactory

object ConfigModule extends TwitterModule with Logging {

  val mode = flag("mode", "dev", "application run mode [dev:default, production]")

  @Provides
  @Singleton
  def provideConfig = config

  protected override def configure(): Unit = {
    install(TypesafeConfigModule.fromConfigWithPackage(config, "id.co.peper.finatra"))
  }

  private lazy val config = {
    info(s"LOADING CONFIG FROM: conf/${mode()}")
    ConfigFactory.load("conf/" + mode())
  }
}
