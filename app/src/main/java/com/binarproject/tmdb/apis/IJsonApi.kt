package com.binarproject.tmdb.apis

import com.binarproject.tmdb.models.ModelListVideos
import com.binarproject.tmdb.models.ModelMovieHeader
import com.binarproject.tmdb.strings.URLCollections
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface IJsonApi {

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_MOVIE + "/{id}")
    suspend fun getMovieDetail(@Path("id") id: String) : ModelMovieHeader

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_MOVIE + "/{id}/" + URLCollections.GET_TRAILERS)
    suspend fun getMovieTrailers(@Path("id") id: String) : ModelListVideos
}