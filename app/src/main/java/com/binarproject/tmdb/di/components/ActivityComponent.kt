package com.binarproject.tmdb.di.components

import com.binarproject.tmdb.di.modules.ActivityModule
import com.binarproject.tmdb.mains.ActivityDetailMovie
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activityDetailMovie: ActivityDetailMovie)
}