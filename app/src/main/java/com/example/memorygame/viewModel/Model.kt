package com.example.memorygame.viewModel

import com.example.memorygame.R

data class Model(
    val dificultad: List<String> = listOf("", "", ""),
    val tiempo: Double,
    val pausa: Boolean,
    val resultado: Int,
    val movimientos: Int,
    var puntuacion: Int = 0


) {

    val tokenListPDNPC = listOf(
        R.drawable.memory1,
        R.drawable.memory2,
        R.drawable.memory3,
        R.drawable.memory4,
        R.drawable.memory5,
    )

    val tokenListProtas = listOf(
        R.drawable.memorytokenpdprotas1,
        R.drawable.memorytokenpdprotas2,
        R.drawable.memorytokenpdprotas3,
        R.drawable.memorytokenpdprotas4,
        R.drawable.memorytokenpdprotas5,
    )

val tokenListIslas = listOf(
        R.drawable.memorytokenislas1,
        R.drawable.memorytokenislas2,
        R.drawable.memorytokenislas3,
        R.drawable.memorytokenislas4,
        R.drawable.memorytokenislas5,
    )

    val iconPDNPC = R.drawable.gamescreeniconpdnpc
    val iconPDProtas = R.drawable.gamescreeniconpdprotas
    val iconIslas = R.drawable.gamescreeniconislas


}