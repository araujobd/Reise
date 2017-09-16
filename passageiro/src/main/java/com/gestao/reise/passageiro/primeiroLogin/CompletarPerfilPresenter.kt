package com.gestao.reise.passageiro.primeiroLogin

import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 13/09/17.
 */
class CompletarPerfilPresenter(val view: CompletarPerfilContrato.View) : CompletarPerfilContrato.Presenter {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val source: DataSource by lazy { DataSourceImpl }

    override fun carregarPerfil() {
        source.buscarPassageiro(auth.currentUser?.uid.toString()) {
            passageiro ->  view.mostrarPerfil(passageiro)
        }
    }

}