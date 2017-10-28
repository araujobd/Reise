package com.gestao.reise.motorista.splash

import android.content.Intent
import com.gestao.reise.motorista.cadastrarViagem.CadastrarViagemActivity
import com.gestao.reise.motorista.perfil.editar.EditarPerfilActivity
import com.gestao.reise.reisecommon.splash.BaseSplashActivity
import com.gestao.reise.reisecommon.splash.SplashContrato

class SplashActivity : BaseSplashActivity() {

    override val presenter: SplashContrato.Presenter by lazy { SplashPresenter(this) }

    override fun iniciarPrincipal() {
        startActivity(Intent(this@SplashActivity, CadastrarViagemActivity::class.java))
        mostrarMensagem("PROXIMA TELA -> Principal")
        finish()
    }

    override fun primeiroLogin() {
        startActivity(Intent(this@SplashActivity, EditarPerfilActivity::class.java))
        mostrarMensagem("PROXIMA TELA -> Completar Login")
        finish()
    }
}
