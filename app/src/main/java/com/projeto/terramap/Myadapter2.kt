package com.projeto.terramap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter2(private val propriedadeList: ArrayList<CadPropriedadeActivity.Propriedade>) : RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_user_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = propriedadeList[position]

        holder.tamanhoHa.text = currentItem.tamanhoHa
        holder.satelite.text = currentItem.satelite
        holder.car.text = currentItem.car
        holder.cultura.text = currentItem.cultura
        holder.municipio.text = currentItem.municipio
    }

    override fun getItemCount(): Int {
        return propriedadeList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tamanhoHa: TextView = itemView.findViewById(R.id.tvtamanhoHa)
        val satelite: TextView = itemView.findViewById(R.id.tvsatelite)
        val car: TextView = itemView.findViewById(R.id.tvcar)
        val cultura: TextView = itemView.findViewById(R.id.tvcultura)
        val municipio: TextView = itemView.findViewById(R.id.tvmunicipio)
    }
}
