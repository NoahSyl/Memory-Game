package com.example.memorygame

sealed class Routes(val route: String) {

    object LaunchScreen : Routes("ListScreen")
    object GameScreen : Routes("GameScreen")
    object MenuScreen: Routes("MenuScreen")
    object ResultScreen: Routes("ResultScreen")

    object HelpScreen: Routes("HelpScreen")

}