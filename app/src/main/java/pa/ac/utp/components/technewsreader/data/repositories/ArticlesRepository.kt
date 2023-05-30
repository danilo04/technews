package pa.ac.utp.components.technewsreader.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.services.LobsterService
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val lobsterService: LobsterService
) {
    suspend fun getHottestArticles(): List<ArticleDto> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = lobsterService.getHottestArticles()
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

     companion object {
         private const val TAG = "ArticlesRepository"
     }
}
