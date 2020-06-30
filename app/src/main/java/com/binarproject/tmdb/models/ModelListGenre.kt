package com.binarproject.tmdb.models
import com.google.gson.annotations.SerializedName

data class ModelListGenre (

	@SerializedName("genres") val genres : List<ModelMovieGenre>
)