package com.projeto.terramap
import com.projeto.terramap.CadUserActivity.Usuario

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

class UserlistActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_userlist)

        userRecyclerview = findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf()
        getUserData()

        val usuarioId = "-NvhtJ8pxf6b-3WFoEBHval"
        updateBtn = findViewById(R.id.updateBtn)

        deleteBtn = findViewById(R.id.deleteBtn)

        deleteBtn.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            //val builder = AlertDialog.Builder(this)
            builder.setTitle("Deletar dados")
            builder.setMessage("Voce tem certeza?")
            builder.setCancelable(false)
            builder.setPositiveButton("Sim") {_, _ ->
                // Assuming usuariosId is defined somewhere
                database.child("usuarios").child(usuarioId).removeValue()
                Toast.makeText(this, "Deletado", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Não") {_, _ ->}
            val alertDialog = builder.create()
            alertDialog.show()
        }
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