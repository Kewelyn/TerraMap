package com.projeto.terramap

import android.app.Notification.Action
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.provider.MediaStore
import android.graphics.Bitmap
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
//import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.navigateUp
//import androidx.navigation.ui.setupActionBarWithNavController
import com.projeto.terramap.databinding.ActivityCadUserBinding

class CadUserActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 100
    lateinit var imageView: ImageView
    lateinit var button: Button


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCadUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Move the setOnClickListener after inflating the layout
        binding.btCadLogin.setOnClickListener {
            IrparaTelaLogin()
        }

        //setSupportActionBar(binding.toolbar)

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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

        //val navController = findNavController(R.id.nav_host_fragment_content_cad_user)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)

    override fun onStart() {
        super.onStart()
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "cadastro de usuário"
    }
        private fun IrparaTelaLogin() {
            val LoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(LoginActivity)
        }
}


    //override fun onSupportNavigateUp(): Boolean {
    //    val navController = findNavController(R.id.nav_host_fragment_content_cad_user)
    //    return navController.navigateUp(appBarConfiguration)
    //            || super.onSupportNavigateUp()
    //}