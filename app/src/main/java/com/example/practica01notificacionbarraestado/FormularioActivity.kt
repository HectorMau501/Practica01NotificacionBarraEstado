package com.example.practica01notificacionbarraestado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NotificationManagerCompat
import com.example.practica01notificacionbarraestado.MenuActivity.Companion.notificationId

class FormularioActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        //Borrar notificacion de barra de estado
        NotificationManagerCompat.from(this).apply {
            cancel(notificationId)
        }

        setSupportActionBar(findViewById(R.id.barraFormulario))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Barra para regresar
    }

    //Metodo para regresar hacia la activity anterior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, MenuActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}