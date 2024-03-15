package com.projeto.terramap

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.projeto.terramap.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // setSupportActionBar(binding.toolbar)

        //binding.buttonEntrar.setOnClickListener {

        //    val email = binding.editTextTextEmailAddress2.text.toString()
        //    val password = binding.editTextTextPassword.text.toString()
        //    IrparaTelaInicial()
        //}

        //binding.buttonCadastrar.setOnClickListener {
        //    IrparaTelaCadUsuario()
        //}
    }
    //fun loginUser(view: View) {
        //val email = binding.editTextTextEmailAddress2.text.toString()
        //val password = binding.editTextTextPassword.text.toString()

        //auth.signInWithEmailAndPassword(email, password)
        //    .addOnCompleteListener(this) { task ->
       //         if (task.isSuccessful) {
       //             // Sign in successful
     //               IrparaTelaInicial()
       //         } else {
                    // Sign in failed
        //            Toast.makeText(baseContext, "Falha no login", Toast.LENGTH_SHORT).show()
        //        }
    //        }
    //}

    //fun Entrar(view: View) {
    //    loginUser(view)
    //}

    fun Entrar(view: View) {
        IrparaTelaInicial()
    }

    private fun IrparaTelaInicial() {
        val homeActivity = Intent(this, homeActivity::class.java)
        startActivity(homeActivity)
    }

    fun CadUsuario(view: View) {
        IrparaTelaCadUsuario()
    }

    private fun IrparaTelaCadUsuario() {
        val cadUserActivity = Intent(this, CadUserActivity::class.java)
        startActivity(cadUserActivity)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_login)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //override fun onSupportNavigateUp(): Boolean {
    //    val navController = findNavController(R.id.nav_host_fragment_content_login)
   //     return navController.navigateUp(appBarConfiguration)
    //            || super.onSupportNavigateUp()
    //}
}