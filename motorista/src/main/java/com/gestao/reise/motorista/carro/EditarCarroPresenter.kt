package com.gestao.reise.motorista.carro

import android.util.Log
import com.gestao.reise.reisecommon.model.Carro
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 05/10/17.
 */
class EditarCarroPresenter(val view: EditarCarroContrato.View) : EditarCarroContrato.Presenter {

    private val source: DataSource = DataSourceImpl
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun carregarCarro() {
        source.buscarCarro(auth.currentUser!!.uid){carro ->
            view.entregarCarro(carro)
        }
    }


    override fun cadastrarCarro(cor: String, modelo: String, placa: String, qtd_vagas: Int, vaga_crianca: Boolean) {
        val uid = auth.currentUser?.uid!!
        val carro = Carro()

        carro.cor = cor
        carro.modelo = modelo
        carro.placa = placa
        carro.qtd_vagas = qtd_vagas
        carro.vaga_crianca = vaga_crianca

        Log.i("CadastrarCarro", carro.toString())
        source.salvarCarro(uid, carro)
        view.done()
    }
}