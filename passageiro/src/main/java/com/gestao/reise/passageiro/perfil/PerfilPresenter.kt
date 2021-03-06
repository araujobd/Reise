package com.gestao.reise.passageiro.perfil

import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 10/09/17.
 */
class PerfilPresenter(val view: PerfilContrato.View) : PerfilContrato.Presenter {

    private val auth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun carregarPerfil() {
        source.buscarPerfilPassageiro(auth.currentUser?.uid!!) {
            passageiro ->  view.mostrarPerfil(passageiro)
        }
    }

    override fun atualizarPerfil(passageiro: Passageiro) {
        source.salvarPassageiro(passageiro)
    }

}