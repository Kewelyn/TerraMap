package com.projeto.terramap

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.projeto.terramap.databinding.ActivityCadUserBinding

class CadUserActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCadUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Move the setOnClickListener after inflating the layout
        binding.btCadLogin.setOnClickListener {
            IrparaTelaLogin()
        }

        val navController = findNavController(R.id.nav_host_fragment_content_cad_user)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onStart() {
        super.onStart()
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Bem-vindo à tela de cadastro de usuário!"
    }
        private fun IrparaTelaLogin() {
            val LoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(LoginActivity)
        }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_cad_user)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}