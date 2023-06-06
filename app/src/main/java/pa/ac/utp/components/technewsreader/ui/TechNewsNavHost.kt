package pa.ac.utp.components.technewsreader.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pa.ac.utp.components.technewsreader.ui.screens.CommentsScreen
import pa.ac.utp.components.technewsreader.ui.screens.MainScreen
import pa.ac.utp.components.technewsreader.ui.screens.NewsScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun TechNewsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "hottest"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("hottest") {
            MainScreen(navController)
        }
        composable("news") {
            NewsScreen(navController)
        }
        composable("comments/{commentsUrl}") {
            val commentsUrl = it.arguments?.getString("commentsUrl")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            } ?: ""
            CommentsScreen(commentsUrl = commentsUrl)
        }
    }
}
