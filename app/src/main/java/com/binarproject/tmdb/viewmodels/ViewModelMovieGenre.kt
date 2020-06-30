package com.binarproject.tmdb.viewmodels

import android.graphics.Color
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binarproject.tmdb.R
import com.binarproject.tmdb.interfaces.IViewHolderClick

class ViewModelMovieGenre(private val listener: IViewHolderClick) : ViewModel() {

    val id: MutableLiveData<Int> = MutableLiveData()
    val genre: MutableLiveData<String> = MutableLiveData()

    fun onClick(v: View) {
        listener.onClick(v, id.value.toString())
    }
}