package com.example.movies2coders.fragments.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies2coders.model.Movies
import com.example.movies2coders.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {
    val moviesList = MutableStateFlow(Movies())
    val loading = MutableStateFlow(false)

    fun getMovies() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMovies()
            if (response.isSuccessful) {
                moviesList.value = response.body() ?: Movies()
                Log.v("GenresVM", "Todo fenomenal en la petición de generos")
            } else {
                Log.v("GenresVM", "Error en la petición de generos ${response.toString()}")
            }

            loading.value = false
        }
    }
}