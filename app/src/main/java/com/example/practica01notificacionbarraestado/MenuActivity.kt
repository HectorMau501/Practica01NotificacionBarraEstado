package com.example.practica01notificacionbarraestado

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MenuActivity : AppCompatActivity() {

    //Instancias
    private lateinit var rgOption: RadioGroup
    private lateinit var date: RadioButton
    private lateinit var service: RadioButton

    //Notificaciones
    private val CHANNEL_ID = "Canal_notificacion"
    private lateinit var pendingIntentSI: PendingIntent
    private lateinit var pendingIntentNO: PendingIntent
//    private val textTile = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Asociar
        date = findViewById(R.id.rbCita)
        service = findViewById(R.id.rbServicios)

        //Barra de estado
        setSupportActionBar(findViewById(R.id.barraMenu))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val btnAplicar: View = findViewById(R.id.btnAplicar)
        btnAplicar.setOnClickListener { aplicar()}

    }

     fun aplicar() {

        if (date.isChecked) {
            createNotificationChannel()
            configureActions()
            buildNotification()
            Toast.makeText(this, "Cita", Toast.LENGTH_SHORT).show()

        }else{
            notificacionToque()
            Toast.makeText(this, "Servicio ", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun notificacionToque() {
        val intent = Intent(this, InformativaActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.notifications_active_24)
            .setContentTitle("Infomacion")
            .setContentText("Datos de Servicios Ofrecidos")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        //Mostrar notificacion
        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
   }

    //EquivaLENTE EN JAVA: PUBLIC FINAL STATTC
    //Para acender al valor desde otra clase
    companion object{
        const val notificationId = 200
    }

    @SuppressLint("MissingPermission")
    private fun buildNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notifications_active_24)
            .setContentTitle("Clinica Medica 53")
            .setContentText("¿Te gustaría hacer una cita Medica?")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.notifications_active_24, "Si", pendingIntentSI)
            .addAction(R.drawable.notifications_active_24, "No", pendingIntentNO)
            .setAutoCancel(true)

        //Mostrar
        with(NotificationManagerCompat.from(this)){
           notify(notificationId, builder.build())
        }
    }
    private fun configureActions() {
        //Afirmativa
        val accionSi = Intent(this, FormularioActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        pendingIntentSI = PendingIntent.getActivity(this, 0, accionSi,
            PendingIntent.FLAG_IMMUTABLE)

        val accionNo = Intent(this, FormularioActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        pendingIntentNO = PendingIntent.getActivity(this, 0, accionNo,
            PendingIntent.FLAG_IMMUTABLE)
    }
    private fun createNotificationChannel() {
        val name = "Canal_IMC"
        val descriptionText = "Canal medicina"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        //RECUERDA PARA QUE NO TE MARQUE ERROR AQUI DEBES DE CAMBIAR minSdk = 26 EN EL BUILD GRADLE
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    //Metodo para regresar hacia la activity anterior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}