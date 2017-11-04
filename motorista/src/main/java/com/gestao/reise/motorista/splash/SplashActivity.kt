package com.gestao.reise.motorista.splash

import android.content.Intent
import com.gestao.reise.motorista.cadastrarViagem.CadastrarViagemActivity
import com.gestao.reise.motorista.principal.PrincipalActivity
import com.gestao.reise.motorista.perfil.editar.EditarPerfilActivity
import com.gestao.reise.reisecommon.splash.BaseSplashActivity
import com.gestao.reise.reisecommon.splash.SplashContrato

class SplashActivity : BaseSplashActivity() {

    override val presenter: SplashContrato.Presenter by lazy { SplashPresenter(this) }

    override fun iniciarPrincipal() {
        startActivity(Intent(this@SplashActivity, PrincipalActivity::class.java))
        finish()
    }

    override fun primeiroLogin() {
        startActivity(Intent(this@SplashActivity, EditarPerfilActivity::class.java))
        //startActivity(Intent(this@SplashActivity, ::class.java))
        mostrarMensagem("PROXIMA TELA -> Completar Login")
        finish()
    }
}
