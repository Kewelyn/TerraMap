package com.projeto.terramap


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.projeto.terramap.LoginActivity
import com.projeto.terramap.R
import com.projeto.terramap.databinding.ActivityCadUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot

class CadUserActivity : AppCompatActivity() {
    private val REQUEST_CAMERA_PERMISSION = 101
    private val REQUEST_IMAGE_CAPTURE = 100
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var binding: ActivityCadUserBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var btnCadUser: Button
    //private lateinit var editTextTextEmailAddress2: EditText
    //private lateinit var editTextTextPassword: EditText
    //private lateinit var editTextText6: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cad_user)

        binding = ActivityCadUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")

        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                Log.d("TAG", "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

        })

        myRef.setValue("Hello, World! 2222")
        myRef.removeValue()

        //auth = FirebaseAuth.getInstance()

        //btnCadUser = findViewById(R.id.btnCadUser)
        //editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2)
        //editTextTextPassword = findViewById(R.id.editTextTextPassword)
        //editTextText6 = findViewById(R.id.editTextText6)

        //btnCadUser.setOnClickListener {
        //    cadastrarUsuario()
        //}

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

    //private fun cadastrarUsuario() {
    //    val email = editTextTextEmailAddress2.text.toString()
    //    val password = editTextTextPassword.text.toString()
    //    val nome = editTextText6.text.toString()

    //    if (email.isEmpty() || password.isEmpty() || nome.isEmpty()) {
    //        Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
    //        return
    //    }

    //    auth.createUserWithEmailAndPassword(email, password)
    //        .addOnCompleteListener(this) { task ->
    //            if (task.isSuccessful) {
    //                // Sign in success, update UI with the signed-in user's information
    //                val user = auth.currentUser
    //                updateUI(user)
    //            } else {
                    // If sign in fails, display a message to the user.
    //                Toast.makeText(baseContext, "Falha no cadastro", Toast.LENGTH_SHORT).show()
    //            }
     //       }
    //}

    //private fun updateUI(user: FirebaseUser?) {
        // ...
    //}

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
