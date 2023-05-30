package pa.ac.utp.components.technewsreader.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import pa.ac.utp.components.technewsreader.ui.screens.MainScreen

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun TechNewsApp() {
    Surface(tonalElevation = 5.dp) {
        MainScreen()
    }
}
