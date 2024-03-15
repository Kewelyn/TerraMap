package com.projeto.terramap

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
//import com.projeto.terramap.databinding.ActivityLocalizacaoBinding
import com.projeto.terramap.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val CHANNEL_ID = "channelID"
    private val CHANNEL_NAME = "channelName"
    private val NOTIFICATION_ID = 0
    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var binding: ActivityMainBinding
    //private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Seja bem vindo")
            .setContentText("Temos o objetivo de otimizar tarefas e fornecer uma interface" +
                    " intuitiva, para que o usuário possa realizar o manuseio de dados com facilidade, a fim de contribuir" +
                    " com todos os pesquisadores da área e proporcionar eficiência ao utilizá-lo.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Temos o objetivo de otimizar tarefas e fornecer uma interface" +
                            " intuitiva, para que o usuário possa realizar o manuseio de dados com facilidade, a fim de contribuir" +
                            " com todos os pesquisadores da área e proporcionar eficiência ao utilizá-lo.")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager = NotificationManagerCompat.from(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Solicitar permissão para notificação
        } else {
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }

        //binding = ActivityLocalizacaoBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Se a permissão ainda não foi concedida, solicitar ao usuário
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MainActivity.REQUEST_LOCATION_PERMISSION
            )
        } else {
            // Se a permissão foi concedida, obter a localização
            obterLocalizacao()
        }
    }

    private fun obterLocalizacao() {
        // Verificar se a permissão de localização foi concedida
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Se a permissão não foi concedida, não podemos obter a localização
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Log.d("loccc", location.toString())
                    val toast = Toast.makeText(
                        this, "latitude:" +
                                location.latitude.toString() + " longitude:" +
                                location.longitude!!.toString(), Toast.LENGTH_LONG
                    )
                    toast.show()
                } else {
                    Toast.makeText(
                        this,
                        "Não foi possível obter a localização.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            // Verificar se a permissão foi concedida
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permissão concedida, obter a localização
                obterLocalizacao()
            } else {
                // Permissão negada, informar ao usuário
                Toast.makeText(
                    this,
                    "Permissão de localização negada.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    fun Next(view: View) {
        IrparaTelaLogin()
    }

    private fun IrparaTelaLogin() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channelName)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
