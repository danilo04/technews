package pa.ac.utp.components.technewsreader.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsDto(
    @field:Json(name = "author") val author: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "content") val content: String,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "date") val date: String,
)

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @field:Json(name ="category") val category: String,
    @field:Json(name = "data") val news: List<NewsDto>
)
