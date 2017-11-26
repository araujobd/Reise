package com.gestao.reise.motorista.principal

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.facebook.internal.Mutable
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.detalhesViagem.DetalhesActivity
import com.gestao.reise.reisecommon.model.Viagem
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by cainan on 03/10/17.
 */
class Adaptador(val viagens: MutableList<Viagem>): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val vr = LayoutInflater.from(parent?.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(vr)

    }
    fun mostrarDetalhes(context: Context, viagem: Viagem){
        val intent = Intent(context, DetalhesActivity::class.java)
        intent.putExtra("detalhes",viagem)
        context.startActivity(intent)
    }
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val viagem: Viagem = viagens[position]
        holder!!.tituloOrigem!!.text = viagem.origem.toUpperCase()
        holder!!.tituloDestino!!.text = viagem.destino.toUpperCase()
        holder!!.tituloHorario!!.text = viagem.horario
        holder!!.tituloData!!.text = viagem.data
        holder!!.tituloPreco!!.text = viagem.preco
        holder!!.botaoDetalhes!!.setOnClickListener{ mostrarDetalhes(holder.context,viagem) }
        holder!!.botaoRemover!!.setOnClickListener{ dialogo(holder.context,viagem,position) }
    }

    override fun getItemCount(): Int {
        return viagens.size
    }
    fun dialogo(contexto: Context,viagem: Viagem,posicao: Int) {
        val builder = AlertDialog.Builder(contexto)
        builder.setTitle("Deseja cancelar esta viagem?")
        builder.setPositiveButton("Sim", { dialog, which ->
            deletarCard(contexto,viagem.uid,auth.currentUser!!.uid)
            viagens.remove(viagem)
            notifyItemRemoved(posicao)
        })
        builder.setNegativeButton("Não", { dialog, which ->
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }
    fun deletarCard(contexto: Context,uid_viagem: String,uid_mot: String){
        source.removerViagemMotorista(uid_mot,uid_viagem){
            Toast.makeText(contexto,"Viagem cancelada com sucesso!",Toast.LENGTH_SHORT).show()
        }
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tituloOrigem = itemView.findViewById<TextView>(R.id.tv_viagem_origem)
        val tituloDestino = itemView.findViewById<TextView>(R.id.tv_viagem_destino)
        val tituloHorario = itemView.findViewById<TextView>(R.id.tv_horario)
        val tituloData = itemView.findViewById<TextView>(R.id.tv_data)
        val tituloPreco = itemView.findViewById<TextView>(R.id.tv_preco)
        val botaoDetalhes = itemView.findViewById<TextView>(R.id.bt_detalhes)
        val botaoRemover = itemView.findViewById<TextView>(R.id.bt_remover)
        val context = itemView.context
    }

}