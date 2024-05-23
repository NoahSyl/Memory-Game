package com.example.memorygame.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.Routes
import com.example.memorygame.ui.theme.LaunchColor
import com.example.memorygame.ui.theme.MenuColor
import com.example.memorygame.ui.theme.MenuColor2
import com.example.memorygame.viewModel.ViewModel

@Composable
fun MenuScreen(viewModel: ViewModel, navController: NavController) {

    viewModel.MenuBackground()

    var buttonText = "Choose mode"

    var expanded by remember { mutableStateOf(false) }
    val modosDeJuego = listOf("Proyecto Desastre", "Proyecto Desastre NPC", "Campaña Islas")
    var opcionSeleccionada by remember {
        mutableStateOf(modosDeJuego[0])
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painterResource(
                id = R.drawable.menuicon
            ),
            contentDescription = "Imagen principal",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(25.dp)
                .size(300.dp)
                .clip(CircleShape)


        )

        Column {

            ElevatedButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(60.dp)
                    .padding(top = 15.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MenuColor,
                    contentColor = Color.White
                ),
                onClick = { expanded = !expanded })
            {
                Text(text = buttonText)
            }



            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
            ) {

                modosDeJuego.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            expanded = false
                            opcionSeleccionada = item

                            when (item){
                                "Proyecto Desastre" -> viewModel.cambiarProtas()
                                "Proyecto Desastre NPC" -> viewModel.cambiarNPC()
                                "Campaña Islas" -> viewModel.cambiarIslas()
                            }

                        })
                }

            }

        }

        Button(
            modifier = Modifier
                .width(150.dp)
                .height(60.dp)
                .padding(top = 15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MenuColor,
                contentColor = Color.White
            ),
            onClick = {
                navController.navigate(Routes.GameScreen.route)
            }) {

            Text(text = "Play")

        }

        Button(
            modifier = Modifier
                .width(150.dp)
                .padding(top = 15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MenuColor2,
                contentColor = Color.White
            ),
            onClick = {
                navController.navigate(Routes.HelpScreen.route)
            }) {

            Text(text = "Help")

        }

    }

}
