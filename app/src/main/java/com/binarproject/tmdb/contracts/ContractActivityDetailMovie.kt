package com.binarproject.tmdb.contracts

import com.binarproject.tmdb.models.ModelListReviews
import com.binarproject.tmdb.models.ModelListVideos
import com.binarproject.tmdb.models.ModelMovieHeader

class ContractActivityDetailMovie {

    interface IPresenter : BaseContract.Presenter<IView> {
        fun getDetailMovie(id : String)
    }

    interface IView {
        fun mapValue(modelMovieHeader: ModelMovieHeader, modelListVideos: ModelListVideos)
        fun mapReviews(modelListReview: ModelListReviews)
        fun showMessage(b: Boolean, message: String?)
    }
}