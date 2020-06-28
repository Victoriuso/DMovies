package com.binarproject.tmdb.di.modules

import android.app.Activity
import com.binarproject.tmdb.contracts.ContractActivityDetailMovie
import com.binarproject.tmdb.presenters.PresenterActivityDetailMovie
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity : Activity) {

    @Provides
    fun provideActivity() : Activity{
        return activity
    }

    @Provides
    fun providePresenter() : ContractActivityDetailMovie.IPresenter {
        return PresenterActivityDetailMovie()
    }
}