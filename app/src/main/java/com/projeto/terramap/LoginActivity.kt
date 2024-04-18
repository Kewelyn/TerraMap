package com.projeto.terramap

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.EditText
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
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailEditText = findViewById(R.id.editTextTextEmailAddress2)
        passwordEditText = findViewById(R.id.editTextTextPassword)

        auth = FirebaseAuth.getInstance()
    }

    fun Entrar(view: View) {
        IrparaTelaInicial()
        //val email = emailEditText.text.toString()
        //val password = passwordEditText.text.toString()

        //FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
          //  .addOnCompleteListener { task ->
            //    if (task.isSuccessful) {
              //      val user = auth.currentUser
                //    if (user != null) {
                  //      IrparaTelaInicial()
                    //} else {
                      //  Toast.makeText(this, "Usuário não autenticado.", Toast.LENGTH_SHORT).show()
                    //}
                //} else {
                  //  val errorMessage = task.exception?.message ?: "Erro desconhecido"
                    //Toast.makeText(this, "Falha no login: $errorMessage", Toast.LENGTH_SHORT).show()
                //}
            //}
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

}