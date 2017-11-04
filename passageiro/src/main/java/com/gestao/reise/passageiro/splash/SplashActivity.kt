package com.gestao.reise.passageiro.splash

import android.content.Intent
import com.gestao.reise.passageiro.buscarViagem.BuscarViagemActivity
import com.gestao.reise.passageiro.perfil.PerfilActivity
import com.gestao.reise.passageiro.primeiroLogin.CompletarPerfilActivity
import com.gestao.reise.passageiro.principal.PrincipalActivity
import com.gestao.reise.passageiro.perfil.editar.EditarPerfilActivity
import com.gestao.reise.reisecommon.splash.BaseSplashActivity
import com.gestao.reise.reisecommon.splash.SplashContrato

class SplashActivity : BaseSplashActivity() {

    override val presenter: SplashContrato.Presenter by lazy { SplashPresenter(this) }

    override fun iniciarPrincipal() {
        startActivity(Intent(this@SplashActivity, PrincipalActivity::class.java))
        startActivity(Intent(this@SplashActivity, PerfilActivity::class.java))
        finish()
    }

    override fun primeiroLogin() {
        startActivity(Intent(this@SplashActivity, EditarPerfilActivity::class.java))
        finish()
    }

}