package com.gestao.reise.passageiro.splash

import android.util.Log
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 02/09/17.
 */
class SplashPresenter(val view: SplashContrato.View) : SplashContrato.Presenter {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun verificarLogin() {
        if (auth.currentUser != null)
            view.iniciarPrincipal()
        else
            view.logar()
    }

    override fun escolherAcao() {
        source.buscarUidPassageiro(auth.currentUser?.uid.toString(),
               sucesso = { view.iniciarPrincipal() },
               erro = { primeiroLogin() })

    }

    private fun primeiroLogin() {
        val user = auth.currentUser
        val passageiro = Passageiro()

        passageiro.uid = user?.uid.toString()
        passageiro.nome = user?.displayName.toString()
        passageiro.fotoUrl = user?.photoUrl.toString()

        source.salvarPassageiro(passageiro)
        view.primeiroLogin()
    }

}