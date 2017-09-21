package com.gestao.reise.motorista.splash

import com.gestao.reise.reisecommon.splash.BaseSplashActivity
import com.gestao.reise.reisecommon.splash.SplashContrato

class SplashActivity : BaseSplashActivity() {

    override val presenter: SplashContrato.Presenter by lazy { SplashPresenter(this) }

    override fun iniciarPrincipal() {
        //startActivity(this, ?::class.java)
        mostrarMensagem("PROXIMA TELA -> Principal")
        //finish()
    }

    override fun primeiroLogin() {
        //startActivity(this, ?::class.java)
        mostrarMensagem("PROXIMA TELA -> Completar Login")
        //finish()
    }
}
