package pa.ac.utp.components.technewsreader.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentDto(
    @field:Json(name = "comment") val comment: String,
    @field:Json(name = "comment_plain") val commentPlain: String,
    @field:Json(name = "indent_level") val indentLevel: Int,
    @field:Json(name = "score") val score: Int,
    @field:Json(name = "commenting_user") val commentingUser: CommentingUser,
)

@JsonClass(generateAdapter = true)
data class CommentingUser(
    @field:Json(name = "username") val username: String
)
