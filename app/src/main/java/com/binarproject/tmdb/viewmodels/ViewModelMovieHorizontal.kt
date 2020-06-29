package com.binarproject.tmdb.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelMovieHorizontal : ViewModel() {

    var imageUrl : MutableLiveData<String> = MutableLiveData()
    var title : MutableLiveData<String> = MutableLiveData()
    var rating : MutableLiveData<Double> = MutableLiveData()
}