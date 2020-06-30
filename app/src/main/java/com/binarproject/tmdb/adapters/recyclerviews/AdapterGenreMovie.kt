package com.binarproject.tmdb.adapters.recyclerviews

import androidx.lifecycle.ViewModelProvider
import com.binarproject.tmdb.R
import com.binarproject.tmdb.factories.viewmodels.MovieGenreFactory
import com.binarproject.tmdb.interfaces.IViewHolderClick
import com.binarproject.tmdb.models.ModelMovieGenre
import com.binarproject.tmdb.viewmodels.ViewModelMovieGenre

class AdapterGenreMovie(private val genres : ArrayList<ModelMovieGenre>, private val listener : IViewHolderClick) :
    BaseAdapterRecyclerView<ViewModelMovieGenre>() {

    override fun getObjectForPosition(position: Int): ViewModelMovieGenre {
        val genre = genres[position]
        val viewModel = MovieGenreFactory(listener).create(ViewModelMovieGenre::class.java)
        viewModel.id.value = genre.id
        viewModel.genre.value = genre.name
        return viewModel
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.vh_movie_genre
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}