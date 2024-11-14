package com.app.mariobros.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.mariobros.MainActivity
import com.app.mariobros.R

/**
 * La actividad SplashActivity muestra una pantalla de bienvenida por un breve período antes de
 * navegar a la actividad principal (MainActivity).
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    /**
     * Este método se llama cuando la actividad se está creando.
     * @param savedInstanceState Si la actividad se está recreando desde un estado previamente guardado, este es el estado.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)  // Establece el contenido de la vista de la actividad desde un recurso de diseño

        // Simula el splash screen por 3 segundos antes de pasar a MainActivity
        Handler().postDelayed({
            // Crea un Intent para iniciar MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)  // Inicia MainActivity
            finish()  // Finaliza SplashActivity para que el usuario no pueda volver a ella presionando el botón de retroceso
        }, 3000) // 3000 ms = 3 segundos
    }
}
