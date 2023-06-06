package pa.ac.utp.components.technewsreader.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fireplace
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

@Composable
fun BottomBar(navController: NavHostController, onClick: (BottomNavItem) -> Unit) {
    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Lobster",
            route = "hottest",
            icon = Icons.Rounded.Fireplace,
        ),
        BottomNavItem(
            name = "News",
            route = "news",
            icon = Icons.Rounded.NotificationsActive,
        ),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        bottomNavItems.forEachIndexed{ index, item ->
            val selected = item.route == currentRoute

            NavigationBarItem(
                selected = selected,
                onClick = {
                    onClick(item)
                },
                label = {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "${item.name} Icon",
                    )
                }
            )
        }
    }
}
