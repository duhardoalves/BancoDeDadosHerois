package com.example.bancodedados.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bancodedados.FormularioActivity
import com.example.bancodedados.R
import com.example.bancodedados.activity.Herois

class RecyclerAdapter(val context: Context, val listaHerois: List<Herois>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
        // Criar a referência das views dos itens com os conteúdos a ser preenchida
        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            fun CriarItem(heroi: Herois){
                val nome = itemView.findViewById<TextView>(R.id.txtNome)
                nome.text = heroi.nomeHeroi

                val poder = itemView.findViewById<TextView>(R.id.txtPoder)
                poder.text = heroi.poderHeroi

                val planeta = itemView.findViewById<TextView>(R.id.txtPlaneta)
                planeta.text = heroi.planetaOrigem
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        val inflater = LayoutInflater.from(context)
        val conteudo = inflater.inflate(R.layout.heroi_item_activity, parent, false)

        return ViewHolder(conteudo)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val herois = listaHerois[position]
        holder.CriarItem(herois)

        holder.itemView.setOnClickListener(){

            Herois.SalvarPreferencias(context, listaHerois[position])

            val intent = Intent(context, FormularioActivity::class.java)
            (holder.itemView.context as Activity).startActivityForResult(intent, 1)
        }
    }

    override fun getItemCount(): Int {
        return listaHerois.size
    }
}