package com.binarproject.tmdb.apis

import com.binarproject.tmdb.models.*
import com.binarproject.tmdb.strings.URLCollections
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IJsonApi {

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_MOVIE + "/{id}")
    suspend fun getMovieDetail(@Path("id") id: String): ModelMovieHeader

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_MOVIE + "/{id}/" + URLCollections.GET_TRAILERS)
    suspend fun getMovieTrailers(@Path("id") id: String): ModelListVideos

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_MOVIE + "/{id}/" + URLCollections.GET_REVIEWS)
    suspend fun getMovieReviews(
        @Path("id") id: String,
        @Query("page") page: Int
    ): ModelListReviews

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_MOVIE + "/" + URLCollections.GET_TOP_RATED_MOVIE)
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): ModelListMovies

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_MOVIE + "/" + URLCollections.GET_POPULAR_MOVIE)
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): ModelListMovies

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_GENRES)
    suspend fun getOfficalGenres(): ModelListGenre

    @Headers("Accept:application/json")
    @GET(URLCollections.GET_DISCOVERED_MOVIE)
    suspend fun getDiscoveredMovie(
        @Query("with_genres") genre: String? = null
    ): ModelListMovies
}