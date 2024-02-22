package com.projeto.terramap


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.projeto.terramap.LoginActivity
import com.projeto.terramap.R
import com.projeto.terramap.databinding.ActivityCadUserBinding

class CadUserActivity : AppCompatActivity() {
    private val REQUEST_CAMERA_PERMISSION = 101
    private val REQUEST_IMAGE_CAPTURE = 100
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var binding: ActivityCadUserBinding

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

        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
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
            Toast.makeText(
                this,
                "Erro ao abrir a câmera: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun IrparaTelaLogin() {
        val LoginActivity = Intent(this, LoginActivity::class.java)
        startActivity(LoginActivity)
    }
}
