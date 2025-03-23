package com.example.myrecpie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

   private  val _categroyState = mutableStateOf(RecpieState())
    val categoriesState: State<RecpieState> = _categroyState

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch{
            try{
                val response = recpieService.getCategories()
                _categroyState.value = _categroyState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }catch (e: Exception){
                _categroyState.value.copy(
                    loading = false,
                    error = "Error Fetching categories ${e.message}"
                )
            }
        }
    }

    data class RecpieState(
        val loading:Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}