package com.example.movies2coders.remote

import com.example.movies2coders.model.Movie
import com.example.movies2coders.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
        @Query("sort_by") sort_by: String = ApiService.sort_by
    ): Response<Movies>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") movie_id: String,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
    ): Response<Movie>

}