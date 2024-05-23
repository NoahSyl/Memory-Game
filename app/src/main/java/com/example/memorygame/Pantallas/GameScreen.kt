import android.graphics.Paint.Align
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.memorygame.R
import com.example.memorygame.Routes
import com.example.memorygame.viewModel.ViewModel

@Composable
fun GameScreen(navController: NavController, viewModel: ViewModel) {
    val currentTokenList = viewModel.currentTokenList
    val currentIcon = viewModel.currentIcon

    // Crear 5 pares de imágenes y mezclarlas
    val shuffledTokens = remember { (currentTokenList + currentTokenList).shuffled() }

    // Estado para las cartas seleccionadas
    var selectedCards by remember { mutableStateOf<List<Int>>(emptyList()) }

    // Estado para rastrear las coincidencias
    var matchedCards by remember { mutableStateOf(emptyList<Int>()) }

    // Estado para la rotación de cada carta
    val cardRotation = remember { mutableStateMapOf<Int, Boolean>().apply {
        shuffledTokens.indices.forEach { this[it] = false }
    } }

    // Pantalla principal del juego
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 5.dp)
        ) {
            viewModel.Timer(
                totalTime = 20L * 1000L,
                handleColor = Color.Green,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Color(0xFF37B900),
                modifier = Modifier.size(150.dp)
            )
        }

        // Mostrar las cartas en una cuadrícula dinámica
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(5) { item -> // Crea la columna
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (indiceCol in 0 until 2) {
                        val indiceItem = item * 2 + indiceCol
                        val isFaceUp = cardRotation[indiceItem] ?: false
                        val degrees by animateFloatAsState(
                            targetValue = if (isFaceUp) 180f else 0f,
                            animationSpec = tween(500)
                        )

                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    if (!matchedCards.contains(indiceItem) && selectedCards.size < 2) {
                                        cardRotation[indiceItem] = !isFaceUp
                                        selectedCards = selectedCards + indiceItem

                                        // Incrementar movimientos en el ViewModel
                                        viewModel.movements++
                                    }
                                },
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            if (selectedCards.contains(indiceItem) || matchedCards.contains(indiceItem)) {
                                Image(
                                    painter = painterResource(shuffledTokens[indiceItem]),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(120.dp)
                                        .graphicsLayer {
                                            rotationY = degrees
                                        }
                                )
                            } else {
                                Box(
                                    modifier = Modifier.size(120.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painterResource(currentIcon),
                                        contentDescription = "",
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(selectedCards) {
            if (selectedCards.size == 2) {
                delay(500) // Esperar 1 segundo para mostrar la selección
                if (shuffledTokens[selectedCards[0]] == shuffledTokens[selectedCards[1]]) {
                    matchedCards = matchedCards + selectedCards
                    viewModel.puntuacion++
                } else {
                    // Voltear las cartas de nuevo si no coinciden
                    selectedCards.forEach { cardRotation[it] = false }
                }
                selectedCards = emptyList() // Reiniciar la selección
            }
        }

        if (viewModel.stopGame) {
            navController.navigate(Routes.ResultScreen.route)
        }
    }
}