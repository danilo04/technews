package pa.ac.utp.components.technewsreader.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.dtos.CommentDto
import pa.ac.utp.components.technewsreader.data.dtos.SubmittedUser
import pa.ac.utp.components.technewsreader.ui.theme.LocalPaddings
import pa.ac.utp.components.technewsreader.ui.viewmodels.CommentsViewModel

@Composable
fun CommentsScreen(commentsUrl: String) {
    val viewModel = hiltViewModel<CommentsViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadComments(commentsUrl)
    }

    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is CommentsViewModel.UiState.Comments -> {
            state.articleDto?.let {
                Comments(articleDto = state.articleDto)
            }
        }
        CommentsViewModel.UiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

@Composable
private fun Comments(articleDto: ArticleDto) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(
                horizontal = LocalPaddings.current.mediumPadding,
                vertical = LocalPaddings.current.smallPadding
            )
        ) {
            Header(article = articleDto)
            Spacer(Modifier.height(20.dp))
            Actions()
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(vertical = 10.dp)
                    .background(MaterialTheme.colorScheme.secondary)
            )
            CommentList(articleDto.comments ?: emptyList())
        }
    }
}

@Composable
fun CommentList(comments: List<CommentDto>) {
    val listState = rememberLazyListState()
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .statusBarsPadding()
    ) {
        LazyColumn(state = listState) {
            items(comments) { comment ->
                Comment(comment)
            }
        }
    }
}

@Composable
fun Comment(comment: CommentDto) {
    Column(
        modifier = Modifier.padding(
            vertical = LocalPaddings.current.smallPadding
        )
    ) {
        Text(
            text = "authored by ${comment.commentingUser.username}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(end = LocalPaddings.current.smallPadding)
        )
        Text(text = comment.commentPlain)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.secondary)
        )
    }
}

@Composable
private fun Header(article: ArticleDto) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .statusBarsPadding()
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineMedium,
        )

        Row(modifier = Modifier.padding(top = 2.dp)) {
            Text(
                text = "authored by ${article.submittedUser.username}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(end = LocalPaddings.current.smallPadding)
            )
        }
    }
}

@Composable
private fun Actions() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(50.dp)
            .padding(top = 10.dp)
            .fillMaxWidth()
            .clickable {

            }
    ) {
        Action("Bookmark", Icons.Filled.Bookmark)
        Action("Share", Icons.Filled.Share)
    }
}

@Composable
private fun Action(title: String, icon: ImageVector) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp)
            .padding(end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Bookmark story",
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}


@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
private fun CommentsPreview() {
    val article = ArticleDto(
        title = "Even more hindsight on Vim, Helix and Kakoune",
        description = "",
        commentCount = 26,
        tags = listOf("vim"),
        createdAt = "2023-05-25T01:39:13.000-05:00",
        shortId = "uxy6ge",
        shortIdUrl = "https://lobste.rs/s/uxy6ge",
        commentsUrl = "https://lobste.rs/s/uxy6ge/even_more_hindsight_on_vim_helix_kakoune",
        url = "https://phaazon.net/blog/more-hindsight-vim-helix-kakoune",
        submittedUser = SubmittedUser(username = "sebastien")
    )
    Comments(articleDto = article)
}
