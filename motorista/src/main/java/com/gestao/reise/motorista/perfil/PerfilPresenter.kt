package com.gestao.reise.motorista.perfil

import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 13/09/17.
 */
class PerfilPresenter(val view: PerfilContrato.View) : PerfilContrato.Presenter {

    private val auth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun carregarPerfil() {
        source.buscarMotorista(auth.currentUser?.uid!!) {
            motorista -> view.mostrarPerfil(motorista)
        }
    }
}