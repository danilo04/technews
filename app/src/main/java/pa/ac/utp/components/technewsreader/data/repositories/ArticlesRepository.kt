package pa.ac.utp.components.technewsreader.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.services.LobsterService
import retrofit2.Response
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val lobsterService: LobsterService
) {
    suspend fun getHottestArticles(): List<ArticleDto> = getArticles { lobsterService.getHottestArticles() }
    suspend fun getActiveArticles(): List<ArticleDto> = getArticles { lobsterService.getActiveArticles() }
    suspend fun getRecentArticles(): List<ArticleDto> = getArticles { lobsterService.getRecentArticles() }

    suspend fun getArticles(getArticlesService: suspend () -> Response<List<ArticleDto>>): List<ArticleDto> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = getArticlesService()
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!
                } else {
                    Log.e(TAG, "Error downloading articles. Error code=${response.code()}")
                    emptyList<ArticleDto>()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error downloading articles.", e)
                emptyList<ArticleDto>()
            }
        }

    suspend fun getCommentsForArticle(commentsUrl: String): ArticleDto? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val url = commentsUrl + ".json"
                val response = lobsterService.getCommentsForArticle(url)
                if (response.isSuccessful && response.body() != null) {
                    response.body()!!
                } else {
                    Log.e(TAG, "Error downloading articles. Error code=${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error downloading article.", e)
                null
            }
        }

     companion object {
         private const val TAG = "ArticlesRepository"
     }
}
