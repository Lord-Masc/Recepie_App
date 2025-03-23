package com.example.myrecpie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recpieViewModel: MainViewModel = viewModel()
    val viewState by recpieViewModel.categoriesState

    NavHost(navController=navController, startDestination = Screen.RecepieScreen.route){
        composable(Screen.RecepieScreen.route){
            RecipeScreen(viewState=viewState,navigationToDetail = {

                //Navigate date from Category Screen to Detail Screen
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?: Category("","","","")
            RecepieDetail(category = category)
        }
    }
}