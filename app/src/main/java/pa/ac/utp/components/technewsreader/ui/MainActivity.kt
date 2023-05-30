package pa.ac.utp.components.technewsreader.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import pa.ac.utp.components.technewsreader.ui.theme.TechNewsReaderTheme

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechNewsReaderTheme {
                Surface(tonalElevation = 5.dp) {
                    // A surface container using the 'background' color from the theme
                    TechNewsApp()
                }
            }
        }
    }
}
