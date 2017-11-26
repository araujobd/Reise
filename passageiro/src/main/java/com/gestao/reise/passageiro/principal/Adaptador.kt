package com.gestao.reise.passageiro.principal

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Viagem
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by cainan on 14/10/17.
 */
class Adaptador(val viagens: MutableList<Viagem>): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val vr = LayoutInflater.from(parent?.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(vr)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val viagem: Viagem = viagens[position]
        holder!!.tituloOrigem!!.text = viagem.origem.capitalize()
        holder!!.tituloDestino!!.text = viagem.destino.capitalize()
        holder!!.tituloHorario!!.text = viagem.horario
        holder!!.tituloPreco!!.text = viagem.preco
        holder!!.tituloDia!!.text = viagem.data
        holder!!.botaoRemover.setOnClickListener{ dialogo(holder.context, viagem,position) }

    }

    override fun getItemCount(): Int {
        return viagens.size
    }

    fun dialogo(contexto: Context,viagem: Viagem,posicao: Int) {
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Deseja remover esta viagem?")
        builder.setPositiveButton("Sim", { dialog, which ->
            deletarCard(viagem.uid,viagem.uid_frequencia,"seg")
            viagens.remove(viagem)
            notifyItemRemoved(posicao)
        })
        builder.setNegativeButton("Não", { dialog, which ->
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }
    fun deletarCard(uid_viagem: String,uid_freq: String,dia: String){
        source.removerCard(auth.currentUser!!.uid,uid_viagem,uid_freq,dia)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tituloOrigem = itemView.findViewById<TextView>(R.id.tv_origem_pas)
        val tituloDestino = itemView.findViewById<TextView>(R.id.tv_destino_pas)
        val tituloHorario = itemView.findViewById<TextView>(R.id.tv_horario_pas)
        val tituloPreco = itemView.findViewById<TextView>(R.id.tv_preco_pas)
        val tituloDia = itemView.findViewById<TextView>(R.id.tv_dia_pas)
        val botaoRemover = itemView.findViewById<Button>(R.id.bt_remover)
        val context = itemView.context
    }

}