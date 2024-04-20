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

        binding.deleteBtn.setOnClickListener{
            val car = binding.DeletarPropriedade.text.toString()
            if (car.isNotEmpty()){
                deleteData(car)
            }else{
                Toast.makeText(this, "Digite o número do CAR para excluir uma Propriedade!", Toast.LENGTH_SHORT).show()
            }
        }

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_delete)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
    private fun deleteData(car: String){
        database = FirebaseDatabase.getInstance().getReference("Lista de Propriedades")
        database.child(car).removeValue().addOnSuccessListener {
            binding.DeletarPropriedade.text?.clear()
        }.addOnFailureListener {
            Toast.makeText(this, "Propriedade excluida com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_delete)
    return navController.navigateUp(appBarConfiguration)
            || super.onSupportNavigateUp()
    }
}