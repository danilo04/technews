package pa.ac.utp.components.technewsreader.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.dtos.NewsDto
import pa.ac.utp.components.technewsreader.data.services.InShortService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val inShortService: InShortService
) {
    suspend fun getNews(): List<NewsDto> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = inShortService.getScienceNews()
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.news
            } else {
                Log.e(TAG, "Error downloading articles. Error code=${response.code()}")
                emptyList<NewsDto>()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error downloading articles.", e)
            emptyList<NewsDto>()
        }
    }

    companion object {
        private const val TAG = "NewsRepository"
    }
}
