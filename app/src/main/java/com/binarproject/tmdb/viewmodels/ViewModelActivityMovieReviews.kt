package com.binarproject.tmdb.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelActivityMovieReviews : ViewModel() {

    val totalComment : MutableLiveData<Int> = MutableLiveData()
}