package com.binarproject.tmdb.mains

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarproject.tmdb.R
import com.binarproject.tmdb.adapters.recyclerviews.AdapterReviews
import com.binarproject.tmdb.contracts.ContractActivityDetailMovie
import com.binarproject.tmdb.databinding.ActivityDetailMovieBinding
import com.binarproject.tmdb.di.components.DaggerActivityComponent
import com.binarproject.tmdb.di.modules.ActivityModule
import com.binarproject.tmdb.models.ModelListReviews
import com.binarproject.tmdb.models.ModelListVideos
import com.binarproject.tmdb.models.ModelMovieHeader
import com.binarproject.tmdb.models.ModelReview
import com.binarproject.tmdb.strings.URLCollections
import com.binarproject.tmdb.utils.LayoutUtils
import com.binarproject.tmdb.viewmodels.ViewModelActivityDetailMovie
import javax.inject.Inject

class ActivityDetailMovie : AppCompatActivity(),
    ContractActivityDetailMovie.IView,
    View.OnClickListener {

    @Inject
    lateinit var presenter: ContractActivityDetailMovie.IPresenter

    private lateinit var viewModel: ViewModelActivityDetailMovie
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var adapter: AdapterReviews

    private lateinit var id : String
    private var reviews: ArrayList<ModelReview> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        injectDependencies()
        setRecyclerView()
        setToolbar()
        toggleRecyclerViewComment(false)
        id = intent.getStringExtra("id")
        presenter.attach(this)
        presenter.getDetailMovie(id)
    }

    override fun mapValue(modelMovieHeader: ModelMovieHeader, modelListVideos: ModelListVideos) {
        if (!isFinishing) {
            val countryCount = modelMovieHeader.production_countries.size
            val companiesCount = modelMovieHeader.production_companies.size
            val languageCount = modelMovieHeader.spoken_languages.size
            val genreCount = modelMovieHeader.genres.size

            if (modelListVideos.results.size > 0)
                viewModel.youtubeUrl.value = modelListVideos.results[0].key

            viewModel.title.value = modelMovieHeader.title
            viewModel.posterPath.value =
                URLCollections.IMAGE_SERVER + "w500" + modelMovieHeader.poster_path
            viewModel.synopsis.value = modelMovieHeader.overview
            viewModel.runtime.value = modelMovieHeader.runtime
            viewModel.adult.value = modelMovieHeader.adult.toString()
            viewModel.releaseDate.value = modelMovieHeader.release_date
            viewModel.ratings.value = modelMovieHeader.vote_average
            viewModel.voteCount.value = modelMovieHeader.vote_count

            for (i in 0 until genreCount) {
                viewModel.genres.value += modelMovieHeader.genres[i].name
                if (i != genreCount - 1)
                    viewModel.genres.value += ", "
            }

            for (i in 0 until countryCount) {
                viewModel.country.value += modelMovieHeader.production_countries[i].name
                if (i != countryCount - 1)
                    viewModel.country.value += ", "
            }

            for (i in 0 until companiesCount) {
                viewModel.companies.value += modelMovieHeader.production_companies[i].name
                if (i != companiesCount - 1)
                    viewModel.companies.value += ", "
            }
            for (i in 0 until languageCount) {
                viewModel.languages.value += modelMovieHeader.spoken_languages[i].name
                if (i != languageCount - 1)
                    viewModel.languages.value += ", "
            }

            viewModel.tags.value = modelMovieHeader.tagline
            viewModel.status.value = modelMovieHeader.status
            viewModel.budget.value = modelMovieHeader.budget
            viewModel.revenue.value = modelMovieHeader.revenue
        }
    }

    override fun mapReviews(modelListReview: ModelListReviews) {
        if (!isFinishing) {
            var max = 0
            if (modelListReview.results.size > 5) {
                max = 5
            } else {
                max = modelListReview.results.size
            }

            if (max == 0) {
                toggleRecyclerViewComment(false)
            } else {
                toggleRecyclerViewComment(true)
                for (i in 0 until max) {
                    reviews.add(modelListReview.results[i])
                }
                adapter.notifyItemRangeChanged(0, max)
            }
        }
    }

    override fun showMessage(b: Boolean, message: String?) {
        val title = when (b) {
            true -> getString(R.string.title_success)
            else -> getString(R.string.title_error)
        }
        if (!isFinishing)
            LayoutUtils.showErrorDialog(this, message.toString(), title)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.txtViewLoadComment -> openCommentActivity()
                else -> {
                }
            }
        }
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }


    private fun toggleRecyclerViewComment(b: Boolean) {
        when(b){
            false -> {
                binding.recyclerViewComment.visibility = View.GONE
                binding.txtViewTitleCommentHeader.visibility = View.VISIBLE
            }
            true -> {
                binding.recyclerViewComment.visibility = View.VISIBLE
                binding.txtViewTitleCommentHeader.visibility = View.GONE
            }
        }
    }

    private fun setRecyclerView() {
        adapter = AdapterReviews(reviews)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        val divider: DividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recyclerViewComment.addItemDecoration(divider)
        binding.recyclerViewComment.layoutManager = layoutManager
        binding.recyclerViewComment.isNestedScrollingEnabled = false
        binding.recyclerViewComment.adapter = adapter
    }

    private fun openCommentActivity() {
        val intent = Intent(this, ActivityMovieReviews::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun injectDependencies() {
        val component = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .build()
        component.inject(this)
        viewModel =
            ViewModelProvider.NewInstanceFactory().create(ViewModelActivityDetailMovie::class.java)
        binding = DataBindingUtil.setContentView<ActivityDetailMovieBinding>(
            this,
            R.layout.activity_detail_movie
        ).apply {
            this.lifecycleOwner = this@ActivityDetailMovie
            this.handler = this@ActivityDetailMovie
            model = viewModel
        }
    }
}