package com.app.mariobros.detail

import android.os.Bundle
import android.widget.Toast
import com.app.mariobros.R
import com.app.mariobros.databinding.ActivityDetailBinding
import com.app.mariobros.base.BaseActivity

open class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureDrawer()
        // Configurar el toolbar
        setSupportActionBar(binding.toolbar.toolbar) // Configura el toolbar aquí
        supportActionBar?.title = ""

        // Configura los elementos del detalle
        val imageId = intent.getIntExtra("IMAGE_ID", -1)
        val heading = intent.getStringExtra("HEADING")
        val skill = intent.getStringExtra("SKILL")

        if (imageId != -1 && heading != null) {
            binding.detailImage.setImageResource(imageId)
            binding.detailHeading.text = heading
            binding.detailSkills.text = skill

            Toast.makeText(this, getString(R.string.selected_character, heading), Toast.LENGTH_SHORT).show()
        } else {
            finish() // Cierra la actividad si no hay datos válidos
        }

        // Configura el botón de retroceso en el toolbar
        binding.toolbar.toolbarArrow.setOnClickListener {
            finish()
        }

    }
}
