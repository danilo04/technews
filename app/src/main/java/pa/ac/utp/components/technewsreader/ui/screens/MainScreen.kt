package pa.ac.utp.components.technewsreader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import pa.ac.utp.components.technewsreader.ui.ArticlesUiState
import pa.ac.utp.components.technewsreader.ui.components.ArticleList
import pa.ac.utp.components.technewsreader.ui.components.BottomBar
import pa.ac.utp.components.technewsreader.ui.components.TopBar
import pa.ac.utp.components.technewsreader.ui.viewmodels.ArticlesViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    val viewModel = hiltViewModel<ArticlesViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(navHostController) {
                navHostController.navigate(it.route)
            }
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (val state = uiState.value) {
                    is ArticlesUiState.Articles -> {
                        ArticleList(articles = state.articles) {
                            val commentsUrlEncode = URLEncoder.encode(it.commentsUrl, StandardCharsets.UTF_8.toString())
                            navHostController.navigate("comments/$commentsUrlEncode")
                        }
                    }

                    ArticlesUiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .size(24.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            strokeWidth = 2.dp
                        )
                    }
                }
            }
        }
    }
}
