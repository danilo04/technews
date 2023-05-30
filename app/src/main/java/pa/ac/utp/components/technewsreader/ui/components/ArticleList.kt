package pa.ac.utp.components.technewsreader.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.dtos.SubmittedUser

@Composable
fun ArticleList(articles: List<ArticleDto>) {
    val listState = rememberLazyListState()
    if (articles.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "No Articles Found")
        }
    } else {
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = articles) { _, article ->
                ArticleRow(article = article)
            }
        }
    }
}


@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun ArticleListPreview() {
    val articles = listOf(
        ArticleDto(
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
        ),
        ArticleDto(
            title = "SectorC: A C Compiler in 512 bytes",
            description = "",
            commentCount = 15,
            tags = listOf("vim"),
            createdAt = "2023-05-25T01:39:13.000-05:00",
            shortId = "uxy6ge",
            shortIdUrl = "https://lobste.rs/s/uxy6ge",
            commentsUrl = "https://lobste.rs/s/uxy6ge/even_more_hindsight_on_vim_helix_kakoune",
            url = "https://phaazon.net/blog/more-hindsight-vim-helix-kakoune",
            submittedUser = SubmittedUser(username = "sebastien")
        ),
        ArticleDto(
            title = "Implementing a distributed key-value store on top of implementing Raft in Go",
            description = "",
            commentCount = 0,
            tags = listOf("distributed", "go"),
            createdAt = "2023-05-25T01:39:13.000-05:00",
            shortId = "uxy6ge",
            shortIdUrl = "https://lobste.rs/s/uxy6ge",
            commentsUrl = "https://lobste.rs/s/uxy6ge/even_more_hindsight_on_vim_helix_kakoune",
            url = "https://phaazon.net/blog/more-hindsight-vim-helix-kakoune",
            submittedUser = SubmittedUser(username = "sebastien")
        ),
        ArticleDto(
            title = "Getting meaningful stack traces from Rust tests returning a Result",
            description = "",
            commentCount = 4,
            tags = listOf("testing", "rust"),
            createdAt = "2023-05-25T01:39:13.000-05:00",
            shortId = "uxy6ge",
            shortIdUrl = "https://lobste.rs/s/uxy6ge",
            commentsUrl = "https://lobste.rs/s/uxy6ge/even_more_hindsight_on_vim_helix_kakoune",
            url = "https://phaazon.net/blog/more-hindsight-vim-helix-kakoune",
            submittedUser = SubmittedUser(username = "sebastien")
        )
    )

    ArticleList(articles = articles)
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun ArticleListEmptyPreview() {
    ArticleList(articles = emptyList())
}
