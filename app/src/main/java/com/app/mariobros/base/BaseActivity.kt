package com.app.mariobros.toolbar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.mariobros.MainActivity
import com.app.mariobros.R
import com.app.mariobros.list.ListCharacterActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.list_character -> {
                startActivity(Intent(this, ListCharacterActivity::class.java))
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
}
