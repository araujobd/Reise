package com.gestao.reise.passageiro.splash

import android.content.Intent
import com.gestao.reise.passageiro.buscarViagem.BuscarViagemActivity
import com.gestao.reise.passageiro.perfil.PerfilActivity
import com.gestao.reise.passageiro.primeiroLogin.CompletarPerfilActivity
import com.gestao.reise.passageiro.principal.PrincipalActivity
import com.gestao.reise.reisecommon.splash.BaseSplashActivity
import com.gestao.reise.reisecommon.splash.SplashContrato

class SplashActivity : BaseSplashActivity() {

    override val presenter: SplashContrato.Presenter by lazy { SplashPresenter(this) }

    override fun iniciarPrincipal() {
        startActivity(Intent(this@SplashActivity, BuscarViagemActivity::class.java))
        mostrarMensagem("PROXIMA TELA -> Principal")
        finish()
    }

    override fun primeiroLogin() {
        startActivity(Intent(this@SplashActivity, CompletarPerfilActivity::class.java))
        mostrarMensagem("PROXIMA TELA -> Completar Login")
        finish()
    }

}