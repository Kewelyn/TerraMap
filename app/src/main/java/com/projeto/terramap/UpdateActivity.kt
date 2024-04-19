package com.projeto.terramap
import com.projeto.terramap.CadUserActivity.Usuario

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.projeto.terramap.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {

            val nome = binding.userName.text.toString()
            val email = binding.firstName.text.toString()
            val password = binding.lastname.text.toString()

            updateData(nome,email,password)
        }
    }

    private fun updateData(nome: String, email: String, password: String) {
        database = FirebaseDatabase.getInstance().getReference("usuarios")
        val user = mapOf<String, String>(
            "nome" to nome,
            "email" to email,
            "password" to password
        )

        database.child(nome).updateChildren(user).addOnSuccessListener {
            binding.userName.text.clear()
            binding.firstName.text.clear()
            binding.lastname.text.clear()
            Toast.makeText(this, "Atualização bem sucedida", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Falha na atualização", Toast.LENGTH_SHORT).show()
        }
    }
}