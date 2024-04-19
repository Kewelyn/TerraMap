package com.projeto.terramap
import com.projeto.terramap.CadUserActivity.Usuario
import com.projeto.terramap.MyAdapter.OnDeleteClickListener

import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.projeto.terramap.databinding.ActivityUserlistBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*

class UserlistActivity : AppCompatActivity(), OnDeleteClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityUserlistBinding
    private lateinit var database : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<Usuario>
    private lateinit var deleteBtn: Button
    private lateinit var updateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRecyclerview = binding.userList
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf()
        getUserData()

        val usuarioId = "-NvhtJ8pxf6b-3WFoEBHval"

        //deleteBtn = binding.deleteBtn
        //updateBtn = binding.updateBtn

        val adapter = MyAdapter(userArrayList)
        adapter.setOnDeleteClickListener(this) // Passa a própria atividade como o ouvinte de clique
        userRecyclerview.adapter = adapter
    }

    override fun onDeleteClick(position: Int) {
        val usuarioId = userArrayList[position].id  // Ajuste isso para acessar corretamente o ID do usuário
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Deletar dados")
        builder.setMessage("Você tem certeza?")
        builder.setCancelable(false)
        builder.setPositiveButton("Sim") { _, _ ->
            // Remove o item da lista
            userArrayList.removeAt(position)
            userRecyclerview.adapter?.notifyItemRemoved(position)
            // Aqui você pode implementar a lógica para excluir o item do banco de dados
            // Lembre-se de usar o ID do usuário para excluir o registro correspondente
            Toast.makeText(this, "Deletado", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Não") { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun getUserData() {
        database = FirebaseDatabase.getInstance().getReference("usuarios")
        database.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(Usuario::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerview.adapter = MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
