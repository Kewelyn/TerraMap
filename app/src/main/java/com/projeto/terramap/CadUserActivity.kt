package com.projeto.terramap

import android.Manifest
import android.app.Notification.Action
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.provider.MediaStore
import android.graphics.Bitmap
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.content.Context
import android.content.pm.PackageManager
import android.app.PendingIntent
import android.graphics.Color
import android.util.Log
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.ui.AppBarConfiguration
import com.projeto.terramap.databinding.ActivityCadUserBinding
import androidx.core.content.ContextCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.content.BroadcastReceiver


class CadUserActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 100
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var binding: ActivityCadUserBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cad_user)

        binding = ActivityCadUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadUser.setOnClickListener {
            IrparaTelaLogin()
        }

        imageView = findViewById(R.id.fotoUser)
        button = findViewById(R.id.buttonFoto)

        button.setOnClickListener() {
            val tirarfotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(tirarfotoIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Error: " + e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

        button.setOnClickListener() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_IMAGE_CAPTURE
                )
            } else {
                dispatchTakePictureIntent()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        val tirarfotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(tirarfotoIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao abrir a câmera: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("CadUserActivity", "onStart() chamado")
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "cadastro de usuário"

    }

    private fun IrparaTelaLogin() {
            val LoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(LoginActivity)
        }
}
