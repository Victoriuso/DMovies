package com.binarproject.tmdb.models

import com.google.gson.annotations.SerializedName

data class ModelListReviews (

	@SerializedName("id") val id : Int,
	@SerializedName("page") val page : Int,
	@SerializedName("results") val results : List<ModelReview>,
	@SerializedName("total_pages") val total_pages : Int,
	@SerializedName("total_results") val total_results : Int
)