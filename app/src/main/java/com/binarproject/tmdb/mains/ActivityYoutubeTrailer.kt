package com.binarproject.tmdb.mains

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.binarproject.tmdb.R
import com.binarproject.tmdb.databinding.ActivityYoutubeTrailerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import kotlinx.android.synthetic.main.activity_youtube_trailer.*

class ActivityYoutubeTrailer : AppCompatActivity() {

    private lateinit var binding : ActivityYoutubeTrailerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_trailer)
        binding = DataBindingUtil.setContentView<ActivityYoutubeTrailerBinding>(this, R.layout.activity_youtube_trailer)

        val youtubeId = intent.getStringExtra("videoId")
        binding.youtubePlayer.enterFullScreen()
        binding.youtubePlayer.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeId, 0.0f)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayer.release()
    }
}