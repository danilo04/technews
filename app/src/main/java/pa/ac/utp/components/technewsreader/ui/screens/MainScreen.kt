package pa.ac.utp.components.technewsreader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import pa.ac.utp.components.technewsreader.ui.components.ArticleList
import pa.ac.utp.components.technewsreader.ui.components.BottomBar
import pa.ac.utp.components.technewsreader.ui.viewmodels.ArticlesViewModel

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<ArticlesViewModel>()
    val refreshing = viewModel.isRefreshing.collectAsState()
    val uiState = viewModel.uiState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing.value,
        onRefresh = { viewModel.refresh() }
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tech News", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomBar()
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding).pullRefresh(pullRefreshState)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (val state = uiState.value) {
                    is ArticlesViewModel.UiState.Articles -> {
                        ArticleList(articles = state.articles)
                    }
                    ArticlesViewModel.UiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .size(24.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            strokeWidth = 2.dp
                        )
                    }
                }
                PullRefreshIndicator(
                    refreshing = refreshing.value,
                    state = pullRefreshState,
                    modifier = Modifier
                )
            }
        }
    }
}
