package com.binarproject.tmdb.mains

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.binarproject.tmdb.R
import com.binarproject.tmdb.adapters.ViewPagerAdapter
import com.binarproject.tmdb.databinding.ActivityMainScreenBinding
import com.binarproject.tmdb.fragments.FragmentHome

class ActivityMainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        setBinding()
        setFragmentAdapter()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main_screen
        )
    }

    private fun setFragmentAdapter() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentHome())
        binding.viewPager.adapter = adapter
    }
}