package pa.ac.utp.components.technewsreader.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDto(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "comment_count") val commentCount: Int,
    @field:Json(name = "tags") val tags: List<String>,
    @field:Json(name = "created_at") val createdAt: String,
    @field:Json(name = "short_id") val shortId: String,
    @field:Json(name = "short_id_url") val shortIdUrl: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "comments_url") val commentsUrl: String,
    @field:Json(name = "submitter_user") val submittedUser: SubmittedUser,
    @field:Json(name = "comments") val comments: List<CommentDto>? = null
)

@JsonClass(generateAdapter = true)
data class SubmittedUser(
    @field:Json(name = "username") val username: String
)
