package com.projeto.terramap
import com.projeto.terramap.CadUserActivity.Usuario

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.projeto.terramap.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*
import com.google.firebase.auth.FirebaseAuth

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("usuarios")
        auth = FirebaseAuth.getInstance()

        binding.updateBtn.setOnClickListener {
            val nome = binding.userName.text.toString()
            val email = binding.firstName.text.toString()
            val password = binding.lastname.text.toString()

            val currentUser = auth.currentUser
            if (currentUser != null) {
                val userId = currentUser.uid
                updateData(userId, nome, email, password)
            } else {
                Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateData(userId: String, nome: String, email: String, password: String) {
        // Verificar se o nó com o ID do usuário existe no banco de dados
        database.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Atualizar os dados apenas se o nó existir (ou seja, se o usuário estiver autorizado)
                    val user = mapOf<String, String>(
                        "nome" to nome,
                        "email" to email,
                        "password" to password
                    )

                    database.child(userId).updateChildren(user)
                        .addOnSuccessListener {
                            binding.userName.text.clear()
                            binding.firstName.text.clear()
                            binding.lastname.text.clear()
                            Toast.makeText(this@UpdateActivity, "Atualização bem sucedida", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this@UpdateActivity, "Falha na atualização", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this@UpdateActivity, "Usuário não autorizado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@UpdateActivity, "Erro: $error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}