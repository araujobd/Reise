package com.gestao.reise.passageiro.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.passageiro.primeiroLogin.CompletarPerfilActivity
import com.gestao.reise.passageiro.principal.PrincipalActivity
import com.gestao.reise.reisecommon.login.LoginActivity

class SplashActivity : Activity(), SplashContrato.View {

    private val RC_LOGIN = 1
    private lateinit var presenter: SplashContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        presenter = SplashPresenter(this)
        esperar()
    }

    private fun esperar() {
        Handler().postDelayed({ presenter.verificarLogin() }, 2000)
    }

    private fun mostrarMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

    override fun logar() {
        startActivityForResult(Intent(this@SplashActivity, LoginActivity::class.java), RC_LOGIN)
    }

    override fun iniciarPrincipal() {
        startActivity(Intent(this@SplashActivity, PrincipalActivity::class.java))
        mostrarMensagem("PROXIMA TELA -> Principal")
        finish()
    }

    override fun primeiroLogin() {
        startActivity(Intent(this@SplashActivity, CompletarPerfilActivity::class.java))
        mostrarMensagem("PROXIMA TELA -> Completar Login")
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_LOGIN && resultCode == Activity.RESULT_OK) {
            presenter.escolherAcao()
        } else {
            finish()
        }
    }
}
