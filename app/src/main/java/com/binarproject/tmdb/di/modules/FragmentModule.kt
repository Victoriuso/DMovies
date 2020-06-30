package com.binarproject.tmdb.di.modules

import androidx.fragment.app.Fragment
import com.binarproject.tmdb.contracts.ContractFragmentHome
import com.binarproject.tmdb.presenters.PresenterFragmentHome
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private var fragment : Fragment) {

    @Provides
    fun provideFragment() : Fragment {
        return fragment
    }

    @Provides
    fun provideFragmentHomePresenter() : ContractFragmentHome.IPresenter {
        return PresenterFragmentHome()
    }
}