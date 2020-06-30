package com.binarproject.tmdb.contracts

import com.binarproject.tmdb.models.ModelListGenre
import com.binarproject.tmdb.models.ModelListMovies

class ContractFragmentHome {
    interface IPresenter : BaseContract.Presenter<IView> {
        fun getPopularMovie(totalPopularMovies: Int)
        fun getTopRatedMovie(totalTopRatedMovie: Int)
        fun getGenres()
        fun getDiscoveredMovie(selectedGenre: ArrayList<String>)
    }

    interface IView {
        fun mapValueToRvPopularMovie(response: ModelListMovies)
        fun mapValueToRvTopRatedMovie(response: ModelListMovies)
        fun mapValueToRvGenre(response: ModelListGenre)
        fun mapValueToRvDiscoveredMovie(response: ModelListMovies)

    }
}