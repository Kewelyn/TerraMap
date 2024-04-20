package com.projeto.terramap
import com.projeto.terramap.CadPropriedadeActivity.Propriedade

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.projeto.terramap.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

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

    }

    private fun deleteData(car: String) {
        database = FirebaseDatabase.getInstance().reference.child("propriedades")
        val query = database.orderByChild("car").equalTo(car)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        snapshot.ref.removeValue().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                binding.DeletarPropriedade.text?.clear()
                                Toast.makeText(this@DeleteActivity, "Propriedade excluída com sucesso!", Toast.LENGTH_SHORT).show()
                                Log.d("DeleteActivity", "Property deleted successfully")
                            } else {
                                Toast.makeText(this@DeleteActivity, "Erro ao excluir a propriedade!", Toast.LENGTH_SHORT).show()
                                Log.e("DeleteActivity", "Error deleting property: ${task.exception}")
                            }
                        }
                    }
                } else {
                    Toast.makeText(this@DeleteActivity, "Nenhuma propriedade encontrada com o CAR fornecido!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("DeleteActivity", "onCancelled", databaseError.toException())
            }
        })
    }


}