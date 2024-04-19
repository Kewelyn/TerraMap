package com.projeto.terramap


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.graphics.drawable.BitmapDrawable
import java.io.ByteArrayOutputStream

class CadUserActivity : AppCompatActivity() {
    private val REQUEST_CAMERA_PERMISSION = 101
    private val REQUEST_IMAGE_CAPTURE = 100
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cad_user)

        val cadastrarButton: Button = findViewById(R.id.btnCadUser)
        cadastrarButton.setOnClickListener {
            cadastrarUsuario()
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

        database = FirebaseDatabase.getInstance().reference.child("usuarios")
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
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val encodedImage = encodeImage(imageBitmap)
            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun encodeImage(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val byteArrayImage = baos.toByteArray()
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT)
    }

    private fun cadastrarUsuario() {
        val nome: String = findViewById<EditText>(R.id.editTextText6).text.toString()
        val email: String = findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
        val password: String = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

        // Obtenha a imagem do imageView
        val fotoImageView = findViewById<ImageView>(R.id.fotoUser)
        val fotoDrawable = fotoImageView.drawable

        if (fotoDrawable != null && fotoDrawable is BitmapDrawable) {
            val fotoBitmap = fotoDrawable.bitmap
            val encodedImage = encodeImage(fotoBitmap)

            // Criar um ID único para a propriedade
            val usuarioId = database.push().key

            if (usuarioId != null) {
                val usuario = Usuario(usuarioId, nome, email, password, encodedImage)
                database.child(usuarioId).setValue(usuario)
            }
        } else {
            Toast.makeText(this, "Selecione uma imagem", Toast.LENGTH_SHORT).show()
        }
    }

    data class Usuario(
        val id: String = "",
        val nome: String = "",
        val email: String = "",
        val password: String = "",
        val foto: String? = null
    )

}
