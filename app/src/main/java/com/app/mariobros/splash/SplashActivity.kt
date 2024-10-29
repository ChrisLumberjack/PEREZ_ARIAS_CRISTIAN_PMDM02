package com.app.mariobros.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.app.mariobros.MainActivity
import com.app.mariobros.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Muestra la pantalla de splash durante 2-3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            // Una vez que el tiempo ha pasado, iniciar MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad de splash
        }, 3000) // Tiempo en milisegundos (3 segundos)
    }
}