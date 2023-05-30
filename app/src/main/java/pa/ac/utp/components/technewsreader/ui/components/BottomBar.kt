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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

@Composable
fun BottomBar() {
    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Hottest",
            route = "hottest",
            icon = Icons.Rounded.Fireplace,
        ),
        BottomNavItem(
            name = "Active",
            route = "active",
            icon = Icons.Rounded.NotificationsActive,
        ),
        BottomNavItem(
            name = "Recent",
            route = "recent",
            icon = Icons.Rounded.Newspaper,
        ),
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        bottomNavItems.forEachIndexed{ index, item ->
            val selected = index == 0

            NavigationBarItem(
                selected = selected,
                onClick = {  },
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

