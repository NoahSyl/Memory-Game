package com.example.memorygame.viewModel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.memorygame.R
import kotlinx.coroutines.delay

class ViewModel : ViewModel() {

    var model by mutableStateOf(
        Model(
            dificultad = listOf("Fácil", "Medio", "Difícil"),
            movimientos = 0,
            pausa = false,
            resultado = 0,
            tiempo = 0.0
        )
    )
        private set

    /**VARIABLES MODO DE JUEGO**/

    val dificultad = model.dificultad
    var puntuacion = model.puntuacion
    var movements  by mutableIntStateOf(0)
    var tokenListPDNPC by mutableStateOf(model.tokenListPDNPC)
    val tokenListProtas by mutableStateOf(model.tokenListProtas)
    val tokenListIslas by mutableStateOf(model.tokenListIslas)
    var currentTokenList by mutableStateOf(model.tokenListProtas)

    var iconPDNPC by mutableStateOf(model.iconPDNPC)
    var iconPDProtas by mutableStateOf(model.iconPDProtas)
    var iconIslas by mutableStateOf(model.iconIslas)
    var currentIcon by mutableStateOf(model.iconPDNPC)

    /**VARIABLES TIMER**/

    var size by mutableStateOf(IntSize.Zero)
    var value by mutableFloatStateOf(1f)
    var isTimerRunning by mutableStateOf(true)
    var stopGame by mutableStateOf(false)


    fun cambiarNPC() {
        currentTokenList = tokenListPDNPC
        currentIcon = iconPDNPC
    }

    fun cambiarProtas() {
        currentTokenList = tokenListProtas
        currentIcon = iconPDProtas
    }

    fun cambiarIslas() {
        currentTokenList = tokenListIslas
        currentIcon = iconIslas
    }

    @Composable
    fun Timer(
        totalTime: Long,
        handleColor: Color,
        inactiveBarColor: Color,
        activeBarColor: Color,
        modifier: Modifier = Modifier,
        initialValue: Float = 0f,
        strokeWidth: Dp = 5.dp

    ) {

        var currentTime by remember {
            mutableLongStateOf(totalTime)
        }

        LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {

            if (currentTime > 0 && isTimerRunning) {
                delay(100L)
                currentTime -= 100L
                value = currentTime / totalTime.toFloat()
            }

        }

        Box(
            modifier = Modifier
                .onSizeChanged {
                    size = it
                },


            ) {
            Canvas(modifier = modifier) {
                drawArc( //parte gris del arco
                    color = inactiveBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )

                drawArc( //parte verde del arco
                    color = activeBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f * value, //lo multiplicamos por el porcentaje de tiempo que llevamos para decidir donde empieza
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )

            }

            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = (currentTime / 1000L).toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )


            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isTimerRunning || currentTime <= 0L) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                ),
                onClick = {
                    if (currentTime <= 0L) {
                        currentTime = totalTime
                        isTimerRunning = true
                    } else {
                        isTimerRunning = !isTimerRunning
                    }
                }) {
                Text(
                    text = if (isTimerRunning && currentTime >= 0L) "Stop"
                    else if (!isTimerRunning && currentTime >= 0L) "Resume"
                    else "Restart"
                )

                if (currentTime.toInt() == 0){
                    stopGame = true

                }
                else{
                    stopGame = false
                }
            }
        }
    }


    @Composable
    fun HomeScreen() {
        Image(
            painterResource(id = R.drawable.gradient), contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    @Composable
    fun MenuBackground() {
        Image(
            painterResource(id = R.drawable.menubackground), contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}




