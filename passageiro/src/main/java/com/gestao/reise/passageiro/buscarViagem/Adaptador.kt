package com.gestao.reise.passageiro.buscarViagem

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 08/10/17.
 */
class Adaptador(val viagens: MutableList<Viagem>): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val vr = LayoutInflater.from(parent?.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(vr)

    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val viagem: Viagem = viagens[position]
        holder!!.tituloOrigem!!.text = viagem.origem
        holder!!.tituloDestino!!.text = viagem.destino
        holder!!.tituloHorario!!.text = viagem.horario
        holder!!.tituloPreco!!.text = viagem.preco
        holder!!.tituloVagas!!.text = viagem.qtd_vagas.toString()
    }

    override fun getItemCount(): Int {
        return viagens.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tituloOrigem = itemView.findViewById<TextView>(R.id.tv_origem)
        val tituloDestino = itemView.findViewById<TextView>(R.id.tv_destino)
        val tituloHorario = itemView.findViewById<TextView>(R.id.tv_horario)
        val tituloPreco = itemView.findViewById<TextView>(R.id.tv_preco)
        val tituloVagas = itemView.findViewById<TextView>(R.id.tv_qtd_vagas)
    }

}