package com.binarproject.tmdb.factories.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binarproject.tmdb.interfaces.IViewHolderClick
import com.binarproject.tmdb.viewmodels.ViewModelMovieHorizontal

class MovieHorizontalFactory(private val listener : IViewHolderClick) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelMovieHorizontal(listener) as T
    }
}