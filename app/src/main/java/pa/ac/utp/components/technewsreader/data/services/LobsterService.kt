package pa.ac.utp.components.technewsreader.data.services

import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface LobsterService {
    @GET("hottest.json")
    suspend fun getHottestArticles(): Response<List<ArticleDto>>

    @GET("active.json?page=1")
    suspend fun getActiveArticles(): Response<List<ArticleDto>>

    @GET("recent.json?page=1")
    suspend fun getRecentArticles(): Response<List<ArticleDto>>

    @GET
    suspend fun getCommentsForArticle(@Url commentsUrl: String): Response<ArticleDto>
}
