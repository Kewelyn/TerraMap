package com.projeto.terramap
import com.projeto.terramap.CadUserActivity.Usuario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.widget.Button

class MyAdapter(private val userList : ArrayList<Usuario>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    interface OnDeleteClickListener {
        fun onDeleteClick(position: Int)
    }

    private var deleteClickListener: OnDeleteClickListener? = null

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        deleteClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.nome.text = currentItem.nome
        holder.email.text = currentItem.email
        Picasso.get().load(currentItem.foto).into(holder.foto)

        holder.deletarButton.setOnClickListener {
            deleteClickListener?.onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.tvfirstName)
        val email: TextView = itemView.findViewById(R.id.tvlastName)
        val foto: ImageView = itemView.findViewById(R.id.imagem_foto)
        val deletarButton: Button = itemView.findViewById(R.id.deleteBtn)
    }
}
