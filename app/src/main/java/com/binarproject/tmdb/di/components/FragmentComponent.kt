package com.binarproject.tmdb.di.components

import com.binarproject.tmdb.di.modules.FragmentModule
import com.binarproject.tmdb.fragments.FragmentHome
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(fragmentHome : FragmentHome)
}