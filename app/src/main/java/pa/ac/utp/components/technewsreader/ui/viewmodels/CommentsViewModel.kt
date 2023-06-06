package pa.ac.utp.components.technewsreader.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.repositories.ArticlesRepository
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val articleRepository: ArticlesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadComments(commentsUrl: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Comments(articleRepository.getCommentsForArticle(commentsUrl))
        }
    }

    sealed interface UiState {
        object Loading : UiState
        data class Comments(val articleDto: ArticleDto?) : UiState
    }
}
