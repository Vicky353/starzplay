package com.example.starzplay.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.starzplay.R
import com.example.starzplay.databinding.DetailScreenBinding
import com.example.starzplay.utils.Constants.Companion.DEFAULT_IMAGE_URL
import com.squareup.picasso.Picasso


class DetailScreen: AppCompatActivity() {
    lateinit var binding: DetailScreenBinding
    var isPlayable:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_screen)

        binding.title.text = intent.getStringExtra("Title")
        var description  = intent.getStringExtra("Description")
        if (description?.isEmpty() == false) {
            binding.des.text = intent.getStringExtra("Description")
        } else {
            binding.des.text = "No description availble"
        }

        isPlayable = intent.getBooleanExtra("isPlayable",false)

        if(isPlayable)
        {
            binding.playable.visibility = View.VISIBLE
        }
        else
        {
            binding.playable.visibility = View.GONE
        }

        Picasso.get().load("${DEFAULT_IMAGE_URL}${intent.getStringExtra("Image")}").placeholder(R.drawable.loader).error(R.drawable.no_image).into(binding.poster)
        binding.poster.setOnClickListener(View.OnClickListener {

            val intentPlayerScreen:Intent = Intent(this,PlayerScreen::class.java)
            intentPlayerScreen.putExtra("MainImage",intent.getStringExtra("Image"))
            startActivity(intentPlayerScreen)
        })

    }
}