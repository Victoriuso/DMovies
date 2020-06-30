package com.binarproject.tmdb.models

import com.google.gson.annotations.SerializedName

data class ModelListMovies (

	@SerializedName("page") val page : Int,
	@SerializedName("total_results") val total_results : Int,
	@SerializedName("total_pages") val total_pages : Int,
	@SerializedName("results") val results : List<ModelMovieSimple>
)