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

    // Declarar el binding y el DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar el binding y configurar la vista principal
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el DrawerLayout desde el archivo de layout
        drawerLayout = binding.drawerLayout

        // Configurar el Toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "" // Sin título en el ActionBar

        // Configurar la vista de navegación lateral (NavigationView)
        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        // Configurar el DrawerToggle para la navegación
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)  // Asociar el DrawerLayout con el toggle
        toggle.syncState()  // Sincronizar el estado del DrawerLayout

        // Si es la primera vez que se abre la actividad, reemplazar el fragmento por defecto (HomeFragment)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())  // Reemplazar el fragmento con HomeFragment
            navigationView.setCheckedItem(R.id.nav_home)  // Marcar el item de inicio como seleccionado
        }

        // Configurar el botón de la flecha en el Toolbar para regresar
        binding.toolbarArrow.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (currentFragment is HomeFragment) {
                finish()  // Si el fragmento actual es HomeFragment, cerrar la actividad
            } else {
                onBackPressed()  // De lo contrario, realizar la acción de "back"
            }
        }
    }

    // Inflar el menú de opciones en el Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        // Cambiar el color de los íconos del menú a blanco
        menu?.let {
            for (i in 0 until it.size()) {
                val item: MenuItem = it.getItem(i)
                item.icon?.setTint(ContextCompat.getColor(this, R.color.white))  // Cambiar color de los íconos
            }
        }
        return true
    }

    // Gestionar las acciones cuando se selecciona un ítem del menú de opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.inicio -> {
                replaceFragment(HomeFragment())  // Reemplazar con HomeFragment
                true
            }

            R.id.list_character -> {
                replaceFragment(ListCharactersFragment())  // Reemplazar con ListCharactersFragment
                true
            }

            R.id.acerca -> {
                mostrarAcercaDeDialog()  // Mostrar el diálogo "Acerca de"
                true
            }

            else -> super.onOptionsItemSelected(item)  // Llamar al manejador por defecto si el ítem no es uno de los anteriores
        }
    }

    // Mostrar el diálogo "Acerca de" con información sobre la aplicación
    private fun mostrarAcercaDeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.about)  // Título del diálogo
            .setMessage(R.string.text_about)  // Mensaje que describe la aplicación
            .setIcon(R.drawable.baseline_menu_24)  // Icono del diálogo
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }  // Botón OK para cerrar el diálogo
        builder.create().show()  // Crear y mostrar el diálogo
    }

    // Gestionar las acciones cuando se selecciona un ítem del menú de navegación lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> replaceFragment(HomeFragment())  // Reemplazar con HomeFragment
            R.id.nav_settings -> replaceFragment(SettingsFragment())  // Reemplazar con SettingsFragment
        }
        return true
    }

    // Método para reemplazar el fragmento actual con uno nuevo
    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)  // Reemplazar el fragmento en el contenedor
        transaction.commit()  // Confirmar la transacción
    }

    // Gestionar la acción de retroceso (Back Button)
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)  // Si el menú lateral está abierto, cerrarlo
        } else {
            super.onBackPressed()  // Si no está abierto, manejar el retroceso normalmente
        }
    }
}


