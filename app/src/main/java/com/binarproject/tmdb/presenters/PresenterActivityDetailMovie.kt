package com.binarproject.tmdb.presenters

import com.binarproject.tmdb.contracts.ContractActivityDetailMovie
import com.binarproject.tmdb.strings.URLCollections
import com.binarproject.tmdb.utils.ConnectionUtils
import kotlinx.coroutines.*
import java.lang.Exception

class PresenterActivityDetailMovie : ContractActivityDetailMovie.IPresenter {

    private lateinit var view: ContractActivityDetailMovie.IView
    private lateinit var scope: CoroutineScope

    private var parentJob: Job = Job()

    override fun getDetailMovie(id: String) {
        scope = CoroutineScope(Dispatchers.Main + parentJob)
        scope.launch {
            try {
                val api = ConnectionUtils.getApi(URLCollections.TOKEN)
                withContext(Dispatchers.IO) {
                    val modelMovieHeader = api.getMovieDetail(id)
                    val modelListVideos = api.getMovieTrailers(id)

                    withContext(Dispatchers.Main) {
                        view.mapValue(modelMovieHeader, modelListVideos)
                    }
                }
            } catch (e: Exception) {
                view.showMessage(false, e.message)
            }
        }
    }

    override fun attach(view: ContractActivityDetailMovie.IView) {
        this.view = view
    }
}