package id.co.peper.finatra.model

case class Meta(status: Int, msg: String)

case class Pagination(totalCount: Int, count: Int, offset: Int)

case class Image(
                  url: String, width: String, height: String, size: Option[String],
                  mp4: Option[String], mp4Size: Option[String],
                  webp: Option[String], webpSize: Option[String]
                )

case class Images(
                   fixedHeight: Option[Image], fixedHeightStill: Option[Image], fixedHeightDownsampled: Option[Image],
                   fixedWidth: Option[Image], fixedWidthStill: Option[Image], fixedWidthDownsampled: Option[Image],
                   fixedHeightSmall: Option[Image], fixedHeightSmallStill: Option[Image],
                   fixedWidthSmall: Option[Image], fixedWidthSmallStill: Option[Image],
                   downsized: Option[Image], downsizedStill: Option[Image],
                   downsizedLarge: Option[Image], downsizedMedium: Option[Image], downsizedSmall: Option[Image],
                   original: Option[Image], originalStill: Option[Image],
                   preview: Option[Image], previewGif: Option[Image]
                 )

case class User(
                 avatarUrl: Option[String], bannerUrl: String, profileUrl: String, username: String,
                 displayName: String, twitter: String
               )


case class Data(
                 `type`: String, id: String, slug: String, url: String, bitlyUrl: String, embedUrl: String,
                 username: String, source: String, rating: String, contentUrl: Option[String], tags: Seq[String],
                 user: Option[User], sourceTld: String, sourcePostUrl: String, updateDatetime: Option[String],
                 createDatetime: Option[String], importDatetime: Option[String], trendingDatetime: Option[String],
                 images: Images
               )

case class GiphyApiSearchResponse(data: Seq[Data], pagination: Pagination, meta: Meta)
