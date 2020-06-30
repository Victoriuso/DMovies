package com.binarproject.tmdb.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binarproject.tmdb.interfaces.IViewHolderClick

class ViewModelMovieHorizontal(val listener: IViewHolderClick) : ViewModel() {

    var id: MutableLiveData<String> = MutableLiveData()
    var imageUrl: MutableLiveData<String> = MutableLiveData()
    var title: MutableLiveData<String> = MutableLiveData()
    var rating: MutableLiveData<Double> = MutableLiveData()

    fun onClick(v: View) {
        listener.onClick(v, id.value.toString())
    }
}