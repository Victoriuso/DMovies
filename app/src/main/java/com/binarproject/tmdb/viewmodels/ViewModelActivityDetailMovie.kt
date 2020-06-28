package com.binarproject.tmdb.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ViewModelActivityDetailMovie : ViewModel() {
    val posterPath : MutableLiveData<String> = MutableLiveData()
    val youtubeUrl : MutableLiveData<String> = MutableLiveData()
    val title : MutableLiveData<String> = MutableLiveData()
    val ratings : MutableLiveData<Double> = MutableLiveData()
    val genres : MutableLiveData<String> = MutableLiveData()
    val releaseDate : MutableLiveData<Date> = MutableLiveData()
    val voteCount : MutableLiveData<Int> = MutableLiveData()

    val synopsis : MutableLiveData<String> = MutableLiveData()
    val runtime : MutableLiveData<Int> = MutableLiveData()
    val adult : MutableLiveData<String> = MutableLiveData()
    val status : MutableLiveData<String> = MutableLiveData()
    val country : MutableLiveData<String> = MutableLiveData()
    val companies : MutableLiveData<String> = MutableLiveData()
    val languages : MutableLiveData<String> = MutableLiveData()
    val tags : MutableLiveData<String> = MutableLiveData()

    val budget : MutableLiveData<Double> = MutableLiveData()
    val revenue : MutableLiveData<Double> = MutableLiveData()

    init {
        genres.value = ""
        country.value = ""
        companies.value = ""
        languages.value = ""
    }
}