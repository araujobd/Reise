package com.gestao.reise.motorista.splash

import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 03/09/17.
 */

class SplashPresenter(val view: SplashContrato.View) :SplashContrato.Presenter {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun verificarLogin() {
        source.buscarMotorista(auth.currentUser?.uid.toString()) {}
        if (auth.currentUser != null)
            view.iniciarPrincipal()
        else
            view.logar()
    }

    override fun escolherAcao() {
        source.buscarUidUser("motoristas", auth.currentUser?.uid.toString(),
                sucesso = { view.iniciarPrincipal() },
                erro = { primeiroLogin() })
    }

    private fun primeiroLogin() {
        val user = auth.currentUser
        val motorista = Motorista()

        motorista.uid = user?.uid.toString()
        motorista.nome = user?.displayName.toString()
        motorista.fotoUrl = user?.photoUrl.toString()

        source.salvarMotorista(motorista)
        view.primeiroLogin()
    }

}