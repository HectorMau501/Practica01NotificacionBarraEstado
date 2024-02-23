package com.example.practica01notificacionbarraestado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class InformativaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informativa)

        setSupportActionBar(findViewById(R.id.barraInformativa))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Metodo para regresar hacia la activity anterior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, MenuActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}