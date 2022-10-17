package com.example.starzplay.activities

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.starzplay.R
import com.example.starzplay.databinding.PlayerScreenBinding

class PlayerScreen : AppCompatActivity() {

    lateinit var binding: PlayerScreenBinding
    var videoUrl =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.player_screen)
        playVideo()
    }

    fun playVideo () {
        val uri: Uri = Uri.parse(videoUrl)
        binding.videoView?.setVideoURI(uri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        mediaController.setMediaPlayer(binding.videoView)
        binding.videoView?.setMediaController(mediaController)
        binding.videoView?.start()
    }
}