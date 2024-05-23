package com.example.memorygame.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.Routes
import com.example.memorygame.ui.theme.HelpColor
import com.example.memorygame.ui.theme.LaunchColor

@Composable

fun HelpScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painterResource(id = R.drawable.gatomaster),
            contentDescription = "gatomaster",
            contentScale = ContentScale.Crop
        // Puedes ajustar el tamaño si es necesario
        )

        Spacer(modifier = Modifier.height(20.dp)) // Espacio entre la imagen y el texto

        Text(
            "You've been:",
            modifier = Modifier.padding(top = 10.dp),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "gatomasterized",
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .width(180.dp)
                .padding(top = 25.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            containerColor = HelpColor,
            onClick = {
                navController.navigate(Routes.MenuScreen.route)
            },
            icon = {
                Icon(
                    Icons.Filled.ArrowBack,
                    "Extended floating action button."
                )
            },
            text = {
                Text(
                    text = "Back",
                    fontSize = 20.sp
                )
            },
        )
    }
}