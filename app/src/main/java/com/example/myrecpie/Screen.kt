package com.example.myrecpie

sealed class Screen(val route: String) {
    object RecepieScreen: Screen("recepie_screen")
    object DetailScreen: Screen("detail_screen")
}