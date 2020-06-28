package com.binarproject.tmdb.models

import com.google.gson.annotations.SerializedName

class ModelSpokenLanguage (

	@SerializedName("iso_639_1") val iso_639_1 : String,
	@SerializedName("name") val name : String
)