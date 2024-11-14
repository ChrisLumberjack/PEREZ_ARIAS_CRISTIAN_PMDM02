package com.app.mariobros

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.app.mariobros.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

/**
 * MainActivity es el punto de entrada para la aplicación.
 * Maneja la configuración del Navigation Drawer y la Toolbar, así como las acciones del menú.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    /**
     * Se llama cuando la actividad está comenzando. Aquí es donde debe ir la mayoría de la inicialización.
     * @param savedInstanceState Si la actividad está siendo re-inicializada después de haber sido previamente cerrada, entonces este Bundle contiene los datos más recientes que se suministraron.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        // Configurar la Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "" // Asegurarse de que el título esté vacío

        // Configurar el DrawerLayout y la barra lateral
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.toolbar, R.string.open, R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar la navegación con el NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        // Configurar la navegación con el AppBarConfiguration para manejar el Toolbar correctamente
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)

        // Configurar el NavigationView
        binding.navView.setNavigationItemSelectedListener { item ->
            onNavigationItemSelected(item, navController)
            true
        }
        binding.toolbarArrow.setOnClickListener {
            onBackPressed()
        }

        // Evitar que el título cambie cuando navegas entre fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)  // Mostrar el ícono de hamburguesa
                setHomeAsUpIndicator(R.drawable.baseline_menu_24) // Ícono de hamburguesa personalizado

                // Opcional: Actualizar el título de la barra de acción si es necesario
                title = ""
            }
        }
    }

    /**
     * Infla el menú de opciones.
     * @param menu El menú de opciones en el que se colocan los ítems.
     * @return Boolean Devuelve verdadero si el menú se infla correctamente.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        menu?.let {
            for (i in 0 until it.size()) {
                val item = it.getItem(i)
                item.icon?.setTint(ContextCompat.getColor(this, R.color.white))
            }
        }
        return true
    }

    /**
     * Maneja las selecciones del menú.
     * @param item El ítem del menú seleccionado.
     * @return Boolean Devuelve verdadero si el ítem se maneja correctamente.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.inicio -> {
                navigateTo(R.id.homeFragment)
                true
            }

            R.id.list_character -> {
                navigateTo(R.id.listCharactersFragment)
                true
            }

            R.id.acerca -> {
                mostrarAcercaDeDialog()  // Mostrar el diálogo "Acerca de"
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Muestra un cuadro de diálogo con información "Acerca de".
     */
    private fun mostrarAcercaDeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.about)  // Título del diálogo
            .setMessage(R.string.text_about)  // Mensaje que describe la aplicación
            .setIcon(R.drawable.baseline_menu_24)  // Icono del diálogo
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }  // Botón OK para cerrar el diálogo
        builder.create().show()  // Crear y mostrar el diálogo
    }

    /**
     * Maneja los ítems seleccionados en el DrawerLayout.
     * @param item El ítem del NavigationView seleccionado.
     * @param navController El controlador de navegación.
     */
    private fun onNavigationItemSelected(item: MenuItem, navController: NavController) {
        when (item.itemId) {
            R.id.nav_home -> {
                navigateTo(R.id.homeFragment, navController)
            }

            R.id.nav_settings -> {
                navigateTo(R.id.settingsFragment, navController)
            }

            else -> {
                // Acción predeterminada
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    /**
     * Navega a un destino específico.
     * @param destinationId El ID del destino al que se debe navegar.
     * @param navController El controlador de navegación, opcionalmente predeterminado al del fragmento contenedor.
     */
    private fun navigateTo(
        destinationId: Int,
        navController: NavController = findNavController(R.id.fragment_container)
    ) {
        // Comprobar si ya estamos en el destino para evitar navegación innecesaria
        if (navController.currentDestination?.id != destinationId) {
            navController.navigate(destinationId)
        }
    }

    /**
     * Maneja la acción de retroceso.
     */
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
