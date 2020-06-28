package com.binarproject.tmdb.utils.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:text")
fun setInt(v: TextView, value: Int?) {
    if (value == null) {
        v.text = ""
    }
    v.text = value.toString()
}

@BindingAdapter("android:text")
fun setDouble(v: TextView, value: Double?) {
    if (value == null) {
        v.text = "0.0"
    }
    value?.let {
        val numberFormat = NumberFormat.getNumberInstance()
        val decimalFormat = numberFormat as DecimalFormat
        decimalFormat.applyPattern("#,###.##")
        val formattedValue = decimalFormat.format(it)
        v.text = formattedValue
    }
}

@BindingAdapter("android:text")
fun setReleaseDate(v: TextView, value: Date? ){
    if(value == null)
        v.text = ""
    value?.let {
        val format = SimpleDateFormat("dd-MM-yyyy")
        val value = format.format(value)
        v.text = value
    }
}

@BindingAdapter("android:src")
fun setImageGlide(v: ImageView, value: String? ){
    if(value != null){
        Glide.with(v.context).load(value).into(v)
    }
}

@BindingAdapter("app:videoId")
fun setYoutubePlayerView(v: YouTubePlayerView, value: String? ){
    if(value != null){
        v.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(value, 0.0f)
            }
        })
    }
}