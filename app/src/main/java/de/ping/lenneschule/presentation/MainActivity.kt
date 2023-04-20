package de.ping.lenneschule.presentation

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import de.ping.lenneschule.presentation.navigation.Navigation
import de.ping.lenneschule.presentation.ui.theme.LennéSchuleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            LennéSchuleTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
    }
}