package id.co.peper.finatra.util

import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization._
import org.json4s.{DefaultFormats, Extraction}

object JsonUtils {

  private implicit val format = DefaultFormats

  def toJson[A <: AnyRef](a: A) = write(a)

  def toJsonSnakeCase(a: Any) = write(Extraction.decompose(a).underscoreKeys)

  def fromJson[A <: AnyRef : Manifest](json: String) = parse(json).extract[A]

  def fromJsonSnakeCase[A <: AnyRef : Manifest](json: String) = parse(json).camelizeKeys.extract[A]
}
