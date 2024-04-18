package com.projeto.terramap
import com.projeto.terramap.CadUserActivity.Usuario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList : ArrayList<Usuario>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.nome.text = currentItem.nome
        holder.email.text = currentItem.email
        holder.password.text = currentItem.password
    }

    override fun getItemCount(): Int {

        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val nome: TextView = itemView.findViewById(R.id.tvfirstName)
        val email: TextView = itemView.findViewById(R.id.tvlastName)
        val password: TextView = itemView.findViewById(R.id.tvage)

    }

}