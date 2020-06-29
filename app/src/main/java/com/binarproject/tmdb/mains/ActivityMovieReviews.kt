package com.binarproject.tmdb.mains

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.binarproject.tmdb.R
import com.binarproject.tmdb.adapters.recyclerviews.AdapterReviews
import com.binarproject.tmdb.contracts.ContractActivityMovieReviews
import com.binarproject.tmdb.databinding.ActivityMovieReviewsBinding
import com.binarproject.tmdb.di.components.DaggerActivityComponent
import com.binarproject.tmdb.di.modules.ActivityModule
import com.binarproject.tmdb.models.ModelListReviews
import com.binarproject.tmdb.models.ModelReview
import com.binarproject.tmdb.viewmodels.ViewModelActivityMovieReviews
import kotlinx.android.synthetic.main.activity_movie_reviews.*
import javax.inject.Inject

class ActivityMovieReviews : AppCompatActivity(), ContractActivityMovieReviews.IView {

    @Inject
    lateinit var presenter: ContractActivityMovieReviews.IPresenter
    private lateinit var viewModel : ViewModelActivityMovieReviews
    private lateinit var binding: ActivityMovieReviewsBinding
    private lateinit var adapter: AdapterReviews

    private var movieId: String = ""
    private var page: Int = 0
    private var reviews: ArrayList<ModelReview> = ArrayList()
    private var isScrolling = false

    enum class SearchType {
        none,
        scroll,
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reviews)
        injectDependencies()
        setToolbar()
        setRecyclerView()
        setSwipeRefreshLayout()
        setNestedScrollView()

        presenter.attach(this)
        movieId = intent.getStringExtra("id").toString()
        if (!movieId.isNullOrEmpty()) {
            startGetListReviews()
        }
    }

    override fun toggleContent(b: Boolean) {
        when(b){
            false -> {
                isScrolling = false
                constraintLayoutLoading.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun mapValueToRecyclerView(listReviews: ModelListReviews) {
        val reviewCount = reviews.size
        val count = listReviews.results.size
        viewModel.totalComment.value = listReviews.total_results
        for (i in 0 until count) {
            reviews.add(listReviews.results[i])
            adapter.notifyItemChanged(reviewCount + i)
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

    private fun setToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    private fun setNestedScrollView() {
        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight) {
                if (!isScrolling) {
                    isScrolling = true
                    constraintLayoutLoading.visibility = View.VISIBLE
                    Handler().postDelayed(
                        Runnable { startGetListReviews(SearchType.scroll) }
                    , 1000)
                }
            }
        }
    }


    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            startGetListReviews()
        }
    }

    private fun startGetListReviews(type: SearchType = SearchType.none) {
        when (type) {
            SearchType.none -> {
                page = 0
                reviews.clear()
                adapter.notifyDataSetChanged()
            }
        }
        page++
        presenter.getListReviews(movieId, page)
    }

    private fun injectDependencies() {
        val component = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .build()
        component.inject(this)
        viewModel = ViewModelProvider.NewInstanceFactory().create(ViewModelActivityMovieReviews::class.java)
        binding = DataBindingUtil.setContentView<ActivityMovieReviewsBinding>(
            this,
            R.layout.activity_movie_reviews
        ).apply {
            this.lifecycleOwner = this@ActivityMovieReviews
            model = viewModel
        }
    }
}