package com.example.memorygame.Pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memorygame.Routes
import com.example.memorygame.ui.theme.MemoryGameTheme
import com.example.memorygame.viewModel.ViewModel

@Composable
fun ResultScreen(navController: NavController, viewModel: ViewModel) {

    var resultado = viewModel.puntuacion
    var movements = viewModel.movements


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

    ) {
        Text(
            "Score:",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "$resultado points",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Movements:",
            modifier = Modifier
                .padding(20.dp),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            " $movements movements",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            modifier = Modifier
                .width(150.dp)
                .padding(top = 45.dp),
            onClick = {
                navController.navigate(Routes.GameScreen.route)
            }) {

            Text(text = "Play again")

        }

        Button(
            modifier = Modifier
                .width(150.dp)
                .padding(top = 15.dp),
            onClick = {
                navController.navigate(Routes.MenuScreen.route)
            }) {

            Text(text = "Menu")

        }
    }


}


