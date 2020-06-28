package com.binarproject.tmdb.models

import com.google.gson.annotations.SerializedName

class ModelMovieGenre (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String
)