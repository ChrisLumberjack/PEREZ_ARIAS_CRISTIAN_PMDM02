package com.app.mariobros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.app.mariobros.databinding.ActivityMainBinding
import com.app.mariobros.list.ListCharacterActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            val intent = Intent(this, ListCharacterActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener{
            exitProcess(1)
        }

        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = ""
        binding.toolbar.toolbarArrow.setOnClickListener{
            finish()
        }
    }
}