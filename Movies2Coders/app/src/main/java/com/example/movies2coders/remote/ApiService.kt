package com.example.movies2coders.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    lateinit var api: Api

    val URL = "https://api.themoviedb.org/3/"
    val URL_IMAGES = "https://image.tmdb.org/t/p/w500/"
    val api_key = "940316ade822c5d291e07d3c812275df"
    val language = "es-ES"
    val sort_by = "popularity.desc"

    init {
        initService()
    }

    private fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

}