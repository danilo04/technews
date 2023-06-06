package pa.ac.utp.components.technewsreader.data.services

import pa.ac.utp.components.technewsreader.data.dtos.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface InShortService {
    @GET("news?category=science")
    suspend fun getScienceNews(): Response<NewsResponse>
}
