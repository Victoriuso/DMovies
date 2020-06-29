package com.binarproject.tmdb.di.modules

import android.app.Activity
import com.binarproject.tmdb.contracts.ContractActivityDetailMovie
import com.binarproject.tmdb.contracts.ContractActivityMovieReviews
import com.binarproject.tmdb.presenters.PresenterActivityDetailMovie
import com.binarproject.tmdb.presenters.PresenterActivityMovieReview
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity : Activity) {

    @Provides
    fun provideActivity() : Activity{
        return activity
    }

    @Provides
    fun provideActivityDetailMoviePresenter() : ContractActivityDetailMovie.IPresenter {
        return PresenterActivityDetailMovie()
    }

    @Provides
    fun provideActivityMovieReviewPresenter() : ContractActivityMovieReviews.IPresenter {
        return PresenterActivityMovieReview()
    }
}