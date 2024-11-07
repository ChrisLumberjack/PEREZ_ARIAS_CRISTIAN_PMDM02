package com.app.mariobros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.app.mariobros.databinding.ActivityMainBinding
import com.app.mariobros.list.ListCharacterActivity
import com.app.mariobros.base.BaseActivity
import kotlin.system.exitProcess

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // Configurar el botón de hamburguesa (abrir el DrawerLayout)
        val toolbarHamburger = findViewById<ImageButton>(R.id.toolbarHamburger)
        toolbarHamburger.setOnClickListener {
            // Abrir el DrawerLayout cuando se presiona el botón de hamburguesa
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}
