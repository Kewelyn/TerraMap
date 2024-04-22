package com.projeto.terramap

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.projeto.terramap.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val tamanhoHa = binding.updatetamanhoHa.text.toString()
            val satelite = binding.updatesatelite.text.toString()
            val car = binding.updatecar.text.toString()
            val cultura = binding.updatecultura.text.toString()
            val municipio = binding.updatemunicipio.text.toString()
            updateData(tamanhoHa,satelite,car,cultura,municipio)
        }
    }
    private fun updateData(tamanhoHa: String, satelite: String, car: String, cultura: String, municipio: String) {
        database = FirebaseDatabase.getInstance().getReference("propriedades")
        val propriedade = mapOf<String, String>(
            "tamanhoHa" to tamanhoHa,
            "satelite" to satelite,
            "car" to car,
            "cultura" to cultura,
            "municipio" to municipio
        )
        database.child(car).setValue(propriedade).addOnSuccessListener {
            binding.updatetamanhoHa.text.clear()
            binding.updatesatelite.text.clear()
            binding.updatecar.text.clear()
            binding.updatecultura.text.clear()
            binding.updatemunicipio.text.clear()
            Toast.makeText(this,"Propriedade atualizada com sucesso",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Falha na atualização",Toast.LENGTH_SHORT).show()
        }
    }

}