package com.app.mariobros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import kotlin.system.exitProcess
import com.app.mariobros.databinding.ActivityMainBinding
import com.app.mariobros.list.ListCharacterActivity
import com.app.mariobros.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el DrawerLayout
        configureDrawer() // Llamada a la función que configura el DrawerLayout

        // Configurar botones de la actividad
        binding.button.setOnClickListener {
            val intent = Intent(this, ListCharacterActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            exitProcess(1)
        }

        // Configurar el Toolbar
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.title = ""

        // Configurar el botón de la flecha en el Toolbar (ir atrás)
        binding.toolbar.toolbarArrow.setOnClickListener {
            finish()
        }
    }
}
