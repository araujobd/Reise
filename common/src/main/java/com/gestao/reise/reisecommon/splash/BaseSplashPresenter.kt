package com.gestao.reise.reisecommon.splash

import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by bruno on 21/09/17.
 */

abstract class BaseSplashPresenter(val view: SplashContrato.View) : SplashContrato.Presenter {

    abstract val typeUser: String
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    protected val source: DataSource by lazy { DataSourceImpl }

    override fun verificarLogin() {
        if (auth.currentUser != null)
            escolherAcao()
        else
            view.logar()
    }

    override fun escolherAcao() {
        source.buscarUidUser(typeUser, auth.currentUser?.uid.toString(),
                sucesso = { view.iniciarPrincipal()},
                erro = { primeiroLogin() })
    }

    private fun primeiroLogin() {
        auth.currentUser?.let { salvarUsuario(it) }
        view.primeiroLogin()
    }

    abstract fun salvarUsuario(user: FirebaseUser)
}