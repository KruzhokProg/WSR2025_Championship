import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import database.DataBaseUtils
import presentation.MainScreen

@Composable
@Preview
fun App() {
    MainScreen()
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
