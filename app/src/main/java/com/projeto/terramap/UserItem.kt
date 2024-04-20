package com.projeto.terramap
import com.projeto.terramap.CadUserActivity.Usuario

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.projeto.terramap.databinding.ActivityUserItemBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class UserItem : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityUserItemBinding
    private lateinit var database : DatabaseReference
    private lateinit var deleteBtn: Button
    private lateinit var updateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_user_item)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val usuarioId = "-NvhtJ8pxf6b-3WFoEBHval"
        deleteBtn = binding.deleteBtn
        updateBtn = binding.updateBtn
        database = FirebaseDatabase.getInstance().reference

        updateBtn = findViewById(R.id.updateBtn)

        deleteBtn = findViewById(R.id.deleteBtn)

        deleteBtn.setOnClickListener {
            Log.d("UserItem", "Botão de deletar clicado")
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Deletar dados")
            builder.setMessage("Você tem certeza?")
            builder.setCancelable(false)
            builder.setPositiveButton("Sim") { _, _ ->
                // Assuming usuarioId is defined somewhere
                database.child("usuarios").child(usuarioId).removeValue()
                Toast.makeText(this, "Deletado", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Não") { _, _ -> }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_user_item)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}