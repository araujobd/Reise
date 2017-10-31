package com.gestao.reise.motorista.detalhesPassageiros

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gestao.reise.motorista.R
import com.gestao.reise.reisecommon.model.Passageiro
import com.squareup.picasso.Picasso

/**
 * Created by cainan on 31/10/17.
 */
class Adaptador(val passageiros: MutableList<Passageiro>): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val vr = LayoutInflater.from(parent?.context).inflate(R.layout.card_view_pas, parent, false)
        return ViewHolder(vr)

    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val passageiro: Passageiro = passageiros[position]
        Picasso.with(holder!!.contexto).load(passageiro.fotoUrl).noFade().into(holder.foto)
        holder!!.nome!!.text = passageiro.nome
        holder!!.endereco!!.text = passageiro.endereco
        holder!!.tel!!.text = passageiro.telefone
        holder!!.desc!!.text = passageiro.descricao
    }

    override fun getItemCount(): Int {
        return passageiros.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foto = itemView.findViewById<ImageView>(R.id.foto_pas)
        val nome = itemView.findViewById<TextView>(R.id.tv_nome_pas)
        val endereco = itemView.findViewById<TextView>(R.id.tv_end_pas)
        val tel = itemView.findViewById<TextView>(R.id.tv_tel_pas)
        val desc = itemView.findViewById<TextView>(R.id.tv_desc_pas)
        val contexto = itemView.context
    }
}