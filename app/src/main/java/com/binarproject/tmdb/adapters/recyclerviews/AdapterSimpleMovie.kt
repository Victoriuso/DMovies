package com.binarproject.tmdb.adapters.recyclerviews

import com.binarproject.tmdb.R
import com.binarproject.tmdb.factories.viewmodels.MovieHorizontalFactory
import com.binarproject.tmdb.interfaces.IViewHolderClick
import com.binarproject.tmdb.models.ModelMovieSimple
import com.binarproject.tmdb.strings.URLCollections
import com.binarproject.tmdb.viewmodels.ViewModelMovieHorizontal

//Adapter untuk tampilan home
class AdapterSimpleMovie(private val movies : ArrayList<ModelMovieSimple>, private val listener : IViewHolderClick) :
    BaseAdapterRecyclerView<ViewModelMovieHorizontal>() {

    override fun getObjectForPosition(position: Int): ViewModelMovieHorizontal {
        val movie = movies[position]
        val viewModel = MovieHorizontalFactory(listener).create(ViewModelMovieHorizontal::class.java)
        viewModel.id.value = movie.id.toString()
        viewModel.imageUrl.value = URLCollections.IMAGE_SERVER + "/w500" + movie.poster_path
        viewModel.title.value = movie.title
        viewModel.rating.value = movie.vote_average
        return viewModel
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.vh_movie_horizontal
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}