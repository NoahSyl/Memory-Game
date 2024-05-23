package com.example.memorygame.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.Routes
import com.example.memorygame.ui.theme.LaunchColor
import com.example.memorygame.ui.theme.MemoryGameTheme
import com.example.memorygame.ui.theme.Purple40
import com.example.memorygame.ui.theme.PurpleGrey80
import com.example.memorygame.viewModel.ViewModel

@Composable
fun LaunchScreen(viewModel: ViewModel, navController: NavController) {

    viewModel.HomeScreen()

    val offset = Offset(2.0f, 5.0f)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 200.dp,
                spotColor = Color.Black,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Image(
            painterResource(
                id = R.drawable.memorygame_icon
            ),
            contentDescription = "Imagen principal",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(25.dp)
                .size(300.dp)
                .clip(CircleShape)


        )

        Text(
            text = "Memory Game ",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            style = TextStyle(
                shadow = Shadow(color = LaunchColor, offset = offset, blurRadius = 5f)
            )
        )


        Text(
            text = "(rol edition)",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            style = TextStyle(
                shadow = Shadow(color = LaunchColor, offset = offset, blurRadius = 5f)
            )
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .width(180.dp)
                .padding(top = 25.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            containerColor = LaunchColor,
            onClick = {
                navController.navigate(Routes.GameScreen.route)
            },
            icon = {
                Icon(
                    Icons.Filled.PlayArrow,
                    "Extended floating action button."
                )
            },
            text = {
                Text(
                    text = "Play!",
                    fontSize = 20.sp
                )
            },
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .width(180.dp)
                .padding(top = 25.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            containerColor = LaunchColor,
            onClick = {
                navController.navigate(Routes.MenuScreen.route)
            },
            icon = {
                Icon(
                    Icons.Filled.Settings,
                    "Extended floating action button."
                )
            },
            text = {
                Text(
                    text = "Menu",
                    fontSize = 20.sp
                )
            },
        )
    }


}

