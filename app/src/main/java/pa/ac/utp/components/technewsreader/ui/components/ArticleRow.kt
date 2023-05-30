package pa.ac.utp.components.technewsreader.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pa.ac.utp.components.technewsreader.data.dtos.ArticleDto
import pa.ac.utp.components.technewsreader.data.dtos.SubmittedUser
import pa.ac.utp.components.technewsreader.ui.theme.LocalPaddings

@Composable
fun ArticleRow(article: ArticleDto) {
    Column(modifier = Modifier.clickable { /* event */ }) {
        Column(
            modifier = Modifier.padding(
                horizontal = LocalPaddings.current.defaultPadding,
                vertical = LocalPaddings.current.smallPadding
            )
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Row(modifier = Modifier.padding(top = 2.dp)) {
                Text(
                    text = "authored by ${article.submittedUser.username}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(end = LocalPaddings.current.smallPadding)
                )
                Text(
                    text = " | "
                )
                Text(
                    text = "${article.commentCount} comments",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = LocalPaddings.current.smallPadding, end = 5.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(MaterialTheme.colorScheme.secondary)
        )
    }
}


@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun ArticleRowPreview() {
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
    
    ArticleRow(article = article)
}
