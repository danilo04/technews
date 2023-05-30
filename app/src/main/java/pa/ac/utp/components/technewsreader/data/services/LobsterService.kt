package pa.ac.utp.components.technewsreader.data.services

import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import retrofit2.Response
import retrofit2.http.GET

interface LobsterService {
    @GET("hottest.json")
    suspend fun getHottestArticles(): Response<List<ArticleDto>>
}
