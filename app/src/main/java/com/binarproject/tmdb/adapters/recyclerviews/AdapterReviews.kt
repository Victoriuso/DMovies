package com.binarproject.tmdb.adapters.recyclerviews

import androidx.lifecycle.ViewModelProvider
import com.binarproject.tmdb.R
import com.binarproject.tmdb.models.ModelReview
import com.binarproject.tmdb.viewmodels.ViewModelReviews

class AdapterReviews(private val reviews : ArrayList<ModelReview>)  : BaseAdapterRecyclerView<ViewModelReviews>() {

    override fun getObjectForPosition(position: Int): ViewModelReviews {
        val review = reviews[position]
        val viewModel = ViewModelProvider.NewInstanceFactory().create(ViewModelReviews::class.java)
        viewModel.author.value = review.author
        viewModel.comment.value = review.content
        return viewModel
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.vh_movie_reviews
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}