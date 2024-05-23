package com.example.memorygame

import GameScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memorygame.Pantallas.HelpScreen
import com.example.memorygame.Pantallas.LaunchScreen
import com.example.memorygame.Pantallas.MenuScreen
import com.example.memorygame.Pantallas.ResultScreen
import com.example.memorygame.ui.theme.MemoryGameTheme
import com.example.memorygame.viewModel.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MemoryGameTheme {
                // A surface container using the 'background' color from the theme

                val navigationController = rememberNavController()
                val myViewModel by viewModels<ViewModel>()


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.LaunchScreen.route
                    ) {
                        composable(Routes.LaunchScreen.route){ LaunchScreen(myViewModel, navigationController)}
                        composable(Routes.GameScreen.route){GameScreen(navigationController, myViewModel)}
                        composable(Routes.MenuScreen.route){ MenuScreen(myViewModel, navigationController )}
                        composable(Routes.ResultScreen.route){ ResultScreen(navigationController, myViewModel)}
                        composable(Routes.HelpScreen.route){ HelpScreen(navigationController)}
                    }
                }
            }
        }
    }
}