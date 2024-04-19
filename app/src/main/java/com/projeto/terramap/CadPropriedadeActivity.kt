package com.projeto.terramap

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.projeto.terramap.databinding.ActivityCadPropriedadeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CadPropriedadeActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCadPropriedadeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadPropriedadeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar o Firebase
        database = FirebaseDatabase.getInstance().reference.child("propriedades")

        // Botão de Cadastro
        val cadastrarButton: Button = findViewById(R.id.bt_cad_login)
        cadastrarButton.setOnClickListener {
            cadastrarPropriedade()
        }
    }
    private fun cadastrarPropriedade() {
        val tamanhoHa: String = findViewById<EditText>(R.id.editText9).text.toString()
        val satelite: String = findViewById<EditText>(R.id.editText3).text.toString()
        val car: String = findViewById<EditText>(R.id.editText4).text.toString()
        val cultura: String = findViewById<EditText>(R.id.editTextText5).text.toString()
        val municipio: String = findViewById<EditText>(R.id.editTextText6).text.toString()

        // Criar um ID único para a propriedade
        val propriedadeId = database.push().key

        if (propriedadeId != null) {
            val propriedade = Propriedade(propriedadeId, tamanhoHa, satelite, car, cultura, municipio)
            database.child(propriedadeId).setValue(propriedade)
        }
    }

    data class Propriedade(
        val id: String? = "",
        val tamanhoHa: String? = "",
        val satelite: String? = "",
        val car: String? = "",
        val cultura: String? = "",
        val municipio: String? = null
    )

}