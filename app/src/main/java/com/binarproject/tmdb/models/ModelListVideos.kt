package com.binarproject.tmdb.models
import com.google.gson.annotations.SerializedName

data class ModelListVideos (

	@SerializedName("id") val id : Int,
	@SerializedName("results") val results : List<ModelVideos>
)