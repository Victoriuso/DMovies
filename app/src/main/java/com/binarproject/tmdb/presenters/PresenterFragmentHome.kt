package com.binarproject.tmdb.presenters

import com.binarproject.tmdb.contracts.ContractFragmentHome
import com.binarproject.tmdb.utils.ConnectionUtils
import kotlinx.coroutines.*
import java.lang.Exception

class PresenterFragmentHome : ContractFragmentHome.IPresenter {

    private lateinit var view: ContractFragmentHome.IView
    private lateinit var scope: CoroutineScope

    private var parentJob: Job = Job()

    override fun attach(view: ContractFragmentHome.IView) {
        this.view = view
    }

    override fun getPopularMovie(totalPopularMovies: Int) {
        scope = CoroutineScope(Dispatchers.Main + parentJob)
        scope.launch {
            try {
                val api = ConnectionUtils.getApi()
                val response = withContext(Dispatchers.IO){
                    return@withContext api.getPopularMovies(totalPopularMovies)
                }
                view.mapValueToRvPopularMovie(response)
            } catch (e: Exception) {

            }
        }
    }

    override fun getTopRatedMovie(totalTopRatedMovie: Int) {
        scope = CoroutineScope(Dispatchers.Main + parentJob)
        scope.launch {
            try {
                val api = ConnectionUtils.getApi()
                val response = withContext(Dispatchers.IO){
                    return@withContext api.getTopRatedMovies(totalTopRatedMovie)
                }
                view.mapValueToRvTopRatedMovie(response)
            } catch (e: Exception) {

            }
        }
    }

    override fun getGenres() {
        scope = CoroutineScope(Dispatchers.Main + parentJob)
        scope.launch {
            try {
                val api = ConnectionUtils.getApi()
                val response = withContext(Dispatchers.IO){
                    return@withContext api.getOfficalGenres()
                }
                view.mapValueToRvGenre(response)
            } catch (e: Exception) {

            }
        }
    }

    override fun getDiscoveredMovie(selectedGenre: ArrayList<String>) {
        scope = CoroutineScope(Dispatchers.Main + parentJob)
        scope.launch {
            try {
                val genres = selectedGenre.joinToString()
                val api = ConnectionUtils.getApi()
                val response = withContext(Dispatchers.IO){
                    return@withContext api.getDiscoveredMovie(genres)
                }
                view.mapValueToRvDiscoveredMovie(response)
            } catch (e: Exception) {

            }
        }
    }
}