package com.binarproject.tmdb.fragments

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarproject.tmdb.R
import com.binarproject.tmdb.adapters.recyclerviews.AdapterGenreMovie
import com.binarproject.tmdb.adapters.recyclerviews.AdapterSimpleMovie
import com.binarproject.tmdb.contracts.ContractFragmentHome
import com.binarproject.tmdb.databinding.FragmentHomeBinding
import com.binarproject.tmdb.di.components.DaggerFragmentComponent
import com.binarproject.tmdb.di.modules.FragmentModule
import com.binarproject.tmdb.interfaces.IViewHolderClick
import com.binarproject.tmdb.mains.ActivityDetailMovie
import com.binarproject.tmdb.models.ModelListGenre
import com.binarproject.tmdb.models.ModelListMovies
import com.binarproject.tmdb.models.ModelMovieGenre
import com.binarproject.tmdb.models.ModelMovieSimple
import javax.inject.Inject

class FragmentHome : Fragment(), ContractFragmentHome.IView {

    @Inject
    lateinit var presenter: ContractFragmentHome.IPresenter

    private lateinit var binding: FragmentHomeBinding
    private lateinit var cdt: CountDownTimer

    private var totalTopRatedMovie: Int = 0
    private var topRatedMovies: ArrayList<ModelMovieSimple> = ArrayList()
    private var isTopRatedMoviesScroll: Boolean = false

    private var totalPopularMovies: Int = 0
    private var popularMovies: ArrayList<ModelMovieSimple> = ArrayList()
    private var isPopularMoviewScroll: Boolean = false

    private var genreMovie: ArrayList<ModelMovieGenre> = ArrayList()

    private var selectedGenre: ArrayList<String> = ArrayList()
    private var isDiscoveredMovieScroll: Boolean = false
    private var discoveredMovie: ArrayList<ModelMovieSimple> = ArrayList()

    //region RecyclerView Variable
    private lateinit var adapterPopularMovie: AdapterSimpleMovie
    private lateinit var adapterLatestMovie: AdapterSimpleMovie
    private lateinit var adapterGenreMovie: AdapterGenreMovie
    private lateinit var adapterGenreDiscoveredMovie: AdapterSimpleMovie
    //endregion

    private var isDiscoverRunning = false

    //Pada saat semua movie di onclick
    private val listener = object : IViewHolderClick {
        override fun onClick(v: View, params: Any) {
            val id = params.toString()
            val intent = Intent(context, ActivityDetailMovie::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        injectDependecies()
        presenter.attach(this)
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        setRecyclerview()
        startGetData()
        return binding.root
    }

    override fun mapValueToRvPopularMovie(response: ModelListMovies) {
        val size = response.results.size
        val sizeBefore = popularMovies.size
        for (i in 0 until size) {
            popularMovies.add(response.results[i])
        }
        adapterPopularMovie.notifyItemRangeChanged(sizeBefore, sizeBefore + size)
    }

    override fun mapValueToRvGenre(response: ModelListGenre) {
        val size = response.genres.size
        val sizeBefore = genreMovie.size
        for (i in 0 until size) {
            genreMovie.add(response.genres[i])
        }
        adapterGenreMovie.notifyItemRangeChanged(sizeBefore, sizeBefore + size)
    }

    override fun mapValueToRvDiscoveredMovie(response: ModelListMovies) {
        val size = response.results.size
        val sizeBefore = discoveredMovie.size
        for (i in 0 until size) {
            discoveredMovie.add(response.results[i])
        }
        adapterGenreDiscoveredMovie.notifyItemRangeChanged(sizeBefore, sizeBefore + size)
    }

    override fun mapValueToRvTopRatedMovie(response: ModelListMovies) {
        val size = response.results.size
        val sizeBefore = topRatedMovies.size
        for (i in 0 until size) {
            topRatedMovies.add(response.results[i])
        }
        adapterLatestMovie.notifyItemRangeChanged(sizeBefore, sizeBefore + size)
    }

    private fun setRecyclerview() {
        //Popular
        adapterPopularMovie = AdapterSimpleMovie(popularMovies, listener)
        val layoutManager: LinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewPopularMovie.layoutManager = layoutManager
        binding.recyclerViewPopularMovie.isNestedScrollingEnabled = false
        binding.recyclerViewPopularMovie.adapter = adapterPopularMovie
        binding.nestedScrollViewPopularMovie.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            Toast.makeText(context, "asdad", Toast.LENGTH_SHORT).show()
        }


        //Latest
        val layoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterLatestMovie = AdapterSimpleMovie(topRatedMovies, listener)
        binding.recyclerViewLatestMovie.layoutManager = layoutManager2
        binding.recyclerViewLatestMovie.isNestedScrollingEnabled = false
        binding.recyclerViewLatestMovie.adapter = adapterLatestMovie


        //Genre
        val layoutManager3 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterGenreMovie = AdapterGenreMovie(genreMovie, object : IViewHolderClick {
            override fun onClick(v: View, params: Any) {
                val genreId = params.toString()
                if (selectedGenre.contains(genreId)) {
                    v.setBackgroundResource(R.drawable.bg_genre)
                    selectedGenre.remove(genreId)
                } else {
                    v.setBackgroundResource(R.drawable.bg_genre_checked)
                    selectedGenre.add(genreId)
                }

                if (isDiscoverRunning) {
                    cdt.cancel()
                }
                cdt = object : CountDownTimer(1000, 500){
                    override fun onFinish() {
                        isDiscoverRunning = false
                        discoveredMovie.clear()
                        adapterGenreDiscoveredMovie.notifyDataSetChanged()
                        presenter.getDiscoveredMovie(selectedGenre)
                    }

                    override fun onTick(p0: Long) {
                        isDiscoverRunning = true
                    }
                }.start()
            }
        })
        binding.recylerViewGenre.layoutManager = layoutManager3
        binding.recylerViewGenre.isNestedScrollingEnabled = false
        binding.recylerViewGenre.adapter = adapterGenreMovie


        //Discovered Movie
        val layoutManager4 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterGenreDiscoveredMovie = AdapterSimpleMovie(discoveredMovie, listener)
        binding.recyclerViewDiscoveredMovie.layoutManager = layoutManager4
        binding.recyclerViewDiscoveredMovie.isNestedScrollingEnabled = false
        binding.recyclerViewDiscoveredMovie.adapter = adapterGenreDiscoveredMovie
    }

    private fun startGetData() {
        startGetPopularMovie()
        startGetTopRatedMovie()

        presenter.getGenres()
        presenter.getDiscoveredMovie(selectedGenre)
    }

    private fun startGetPopularMovie() {
        totalPopularMovies++
        presenter.getPopularMovie(totalPopularMovies)
    }

    private fun startGetTopRatedMovie() {
        totalTopRatedMovie++
        presenter.getTopRatedMovie(totalTopRatedMovie)
    }

    private fun injectDependecies() {
        val component = DaggerFragmentComponent
            .builder()
            .fragmentModule(FragmentModule(this))
            .build()
        component.inject(this)
    }

}