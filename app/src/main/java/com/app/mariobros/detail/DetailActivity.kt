package com.app.mariobros.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.mariobros.databinding.ActivityDetailBinding

open class DetailActivity : AppCompatActivity(){

        private lateinit var binding: ActivityDetailBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityDetailBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            val imageId = intent.getIntExtra("IMAGE_ID", -1)
            val heading = intent.getStringExtra("HEADING")

            if (imageId != -1 && heading != null) {
                binding.detailImage.setImageResource(imageId)
                binding.detailHeading.text = heading
            } else {

                finish()
            }

            binding.toolbar.toolbarArrow.setOnClickListener {
                finish()
            }
        }
    }