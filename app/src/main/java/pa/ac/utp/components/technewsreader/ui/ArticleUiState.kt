package pa.ac.utp.components.technewsreader.ui

import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto

sealed interface ArticlesUiState {
    object Loading : ArticlesUiState
    data class Articles(val articles: List<ArticleDto>) : ArticlesUiState
}
