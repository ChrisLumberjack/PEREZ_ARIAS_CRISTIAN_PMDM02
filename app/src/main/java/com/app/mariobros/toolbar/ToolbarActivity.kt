package com.app.mariobros.toolbar

import android.os.Bundle
import android.view.View
import com.app.mariobros.base.BaseActivity
import com.app.mariobros.databinding.ActivityToolbarBinding

class ToolbarActivity : BaseActivity() {

    private lateinit var binding: ActivityToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el botón de retroceso
        binding.toolbarArrow.setOnClickListener {
            finish()
        }


    }
}
