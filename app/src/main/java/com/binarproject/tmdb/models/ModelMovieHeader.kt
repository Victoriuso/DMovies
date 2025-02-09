package com.binarproject.tmdb.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ModelMovieHeader(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("belongs_to_collection") val belongs_to_collection: ModelCollections,
    @SerializedName("budget") val budget: Double,
    @SerializedName("genres") val genres: List<ModelMovieGenre>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdb_id: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("production_companies") val production_companies: List<ModelProductionCompany>,
    @SerializedName("production_countries") val production_countries: List<ModelProductionCountry>,
    @SerializedName("release_date") val release_date: Date,
    @SerializedName("revenue") val revenue: Double,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("spoken_languages") val spoken_languages: List<ModelSpokenLanguage>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("vote_count") val vote_count: Int
)