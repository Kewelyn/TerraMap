package com.projeto.terramap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class PropriedadeListActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var userRecyclerview: RecyclerView
    private lateinit var userArrayList: ArrayList<CadPropriedadeActivity.Propriedade>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_propriedade_list)

        userRecyclerview = findViewById(R.id.propriedadeList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf()
        getUserData()
    }

    private fun getUserData() {
        database = FirebaseDatabase.getInstance().getReference("propriedades")
        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val propriedade = userSnapshot.getValue(CadPropriedadeActivity.Propriedade::class.java)
                        propriedade?.let {
                            userArrayList.add(it)
                        }
                    }
                    userRecyclerview.adapter = MyAdapter2(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled
            }
        })
    }
}
