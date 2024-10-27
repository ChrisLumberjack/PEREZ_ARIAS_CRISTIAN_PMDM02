package com.app.mariobros.toolbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.mariobros.databinding.ActivityToolbarBinding

open class ToolbarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar como ActionBar
        setSupportActionBar(binding.toolbar)

        // Configurar botón de retroceso
        binding.toolbarArrow.setOnClickListener {
            finish()
        }
    }
}