package com.projeto.terramap
import com.projeto.terramap.CadPropriedadeActivity.Propriedade

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.projeto.terramap.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast

class DeleteActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityDeleteBinding.inflate(layoutInflater)
     setContentView(binding.root)

        database = FirebaseDatabase.getInstance().reference.child("propriedades")

        binding.deleteBtn.setOnClickListener {
            val car = binding.DeletarPropriedade.text.toString()
            if (car.isNotEmpty()) {
                deleteData(car)
            } else {
                Toast.makeText(this, "Digite o número do CAR para excluir uma Propriedade!", Toast.LENGTH_SHORT).show()
            }
        }

        setSupportActionBar(binding.toolbar)
    }

    private fun deleteData(car: String) {
        // Remove the property from the Firebase Database
        database.child(car).removeValue()
            .addOnSuccessListener {
                // Clear the input field upon successful deletion
                binding.DeletarPropriedade.text?.clear()
                Toast.makeText(this, "Propriedade excluída com sucesso!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao excluir a propriedade!", Toast.LENGTH_SHORT).show()
            }
    }
}