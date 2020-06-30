package com.binarproject.tmdb.factories.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binarproject.tmdb.interfaces.IViewHolderClick
import com.binarproject.tmdb.viewmodels.ViewModelMovieGenre
import com.binarproject.tmdb.viewmodels.ViewModelMovieHorizontal

class MovieGenreFactory(private val listener : IViewHolderClick) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelMovieGenre(listener) as T
    }
}