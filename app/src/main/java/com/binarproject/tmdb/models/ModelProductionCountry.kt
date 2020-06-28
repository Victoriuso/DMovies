package com.binarproject.tmdb.models

import com.google.gson.annotations.SerializedName


class ModelProductionCountry (

	@SerializedName("iso_3166_1") val iso_3166_1 : String,
	@SerializedName("name") val name : String
)