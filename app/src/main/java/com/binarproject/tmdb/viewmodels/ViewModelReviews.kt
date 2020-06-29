package com.binarproject.tmdb.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelReviews : ViewModel() {

    val author : MutableLiveData<String> = MutableLiveData()
    val comment : MutableLiveData<String> = MutableLiveData()
}