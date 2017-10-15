package com.gestao.reise.passageiro.detalhesViagem

import com.gestao.reise.reisecommon.model.Viagem
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by cainan on 15/10/17.
 */
class DetalhesPresenter(val view: DetalhesContrato.view) : DetalhesContrato.presenter{

    private val source: DataSource = DataSourceImpl
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun reservarVaga(viagem: Viagem) {
        source.reservarViagem(viagem.uid,auth.currentUser!!.uid,
                sucesso = {view.msgSucesso()})
    }
}