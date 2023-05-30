package pa.ac.utp.components.technewsreader.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.repositories.ArticlesRepository
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = UiState.Articles(articlesRepository.getHottestArticles())
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            // Doing the data refresh here
            _uiState.value = UiState.Articles(articlesRepository.getHottestArticles())
            // Set _isRefreshing to false to indicate the refresh is complete
            _isRefreshing.emit(false)
        }
    }

    sealed interface UiState {
        object Loading : UiState
        data class Articles(val articles: List<ArticleDto>) : UiState
    }
}
