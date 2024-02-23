package com.example.practica01notificacionbarraestado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Instancias
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asociaciones
        email = findViewById(R.id.edtCorreo)
        password = findViewById(R.id.edtContrasena)

        val btnIngresar: View = findViewById(R.id.btnIngresar)
        btnIngresar.setOnClickListener { aceptar() }
    }

    private fun aceptar() {
        //Validar para que acepte todo
        if(email.text.isNotBlank() && email.text.isNotEmpty()
            && password.text.isNotBlank() && password.text.isNotEmpty()) {
            if(email.text.toString() == "mauhector7@gmail.com" &&
                password.text.toString() == "123"){
                //Iniciar seccion
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Datos Errones",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Falta de completar los campos",Toast.LENGTH_SHORT).show()
        }
    }
}