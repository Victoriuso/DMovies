package com.binarproject.tmdb.contracts

import com.binarproject.tmdb.models.ModelListReviews

class ContractActivityMovieReviews {

    interface IPresenter : BaseContract.Presenter<IView> {
        fun getListReviews(id: String, page :Int)
    }

    interface IView {
        fun mapValueToRecyclerView(listReviews: ModelListReviews)
        fun toggleContent(b: Boolean)
    }
}