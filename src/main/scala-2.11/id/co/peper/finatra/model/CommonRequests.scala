package id.co.peper.finatra.model

import com.twitter.finatra.request.QueryParam

case class GiphySearchRequest(@QueryParam offset: Option[Int] = Some(0), @QueryParam limit: Option[Int] = Some(10),
                              @QueryParam query: String, @QueryParam showNsfw: Option[Boolean] = Some(false))