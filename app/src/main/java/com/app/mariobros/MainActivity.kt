package com.app.mariobros

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.mariobros.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el DrawerLayout

        drawerLayout = binding.drawerLayout


        val toolbar = binding.toolbar.toolbar1
        // Configurar el Toolbar
        setSupportActionBar(binding.toolbar.toolbar1)
        supportActionBar?.title = ""

        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_home)
        }
        // Configurar el botón de la flecha en el Toolbar (ir atrás)
        binding.toolbar.toolbarArrow.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (currentFragment is HomeFragment) {
                finish()  // Si el fragmento actual es HomeFragment, cierra la actividad
            } else {
                onBackPressed()  // De lo contrario, realiza la acción de "back"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        // Cambiar el color de todos los íconos del menú a blanco
        menu?.let {
            for (i in 0 until it.size()) {
                val item: MenuItem = it.getItem(i)
                item.icon?.setTint(ContextCompat.getColor(this, R.color.white))
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.inicio -> {
                replaceFragment(HomeFragment())
                true
            }

            R.id.list_character -> {
                replaceFragment(ListCharactersFragment())
                true
            }

            R.id.acerca -> {
                mostrarAcercaDeDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarAcercaDeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.about)
            .setMessage(R.string.text_about)
            .setIcon(R.drawable.baseline_menu_24)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> replaceFragment(HomeFragment())
            R.id.nav_settings -> replaceFragment(SettingsFragment()) // Nuevo fragmento de ajustes
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()  // Llamar a la superclase para manejar el back correctamente
        }
    }

}

private fun NavigationView.setNavigationItemSelectedListener(
    mainActivity: MainActivity,
    drawerLayout: DrawerLayout,
    open: Int,
    close: Int
) {

}
