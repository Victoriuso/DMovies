package com.binarproject.tmdb.presenters

import com.binarproject.tmdb.contracts.ContractActivityMovieReviews
import com.binarproject.tmdb.strings.URLCollections
import com.binarproject.tmdb.utils.ConnectionUtils
import kotlinx.coroutines.*
import java.lang.Exception

class PresenterActivityMovieReview : ContractActivityMovieReviews.IPresenter {

    private lateinit var view : ContractActivityMovieReviews.IView
    private lateinit var scope : CoroutineScope
    private var parentJob = Job()

    override fun attach(view: ContractActivityMovieReviews.IView) {
        this.view = view
    }

    override fun getListReviews(id: String, page: Int) {
        scope = CoroutineScope(Dispatchers.Main + parentJob)
        scope.launch {
            try {
                val api = ConnectionUtils.getApi()
                val listReviews = withContext(Dispatchers.IO) {
                    return@withContext api.getMovieReviews(id, page)
                }
                view.mapValueToRecyclerView(listReviews)
            }catch (e : Exception) {

            }
            view.toggleContent(false)
        }
    }
}