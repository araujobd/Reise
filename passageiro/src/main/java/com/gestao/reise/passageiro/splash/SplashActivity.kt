package com.gestao.reise.passageiro.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.login.LoginActivity

class SplashActivity : Activity(), SplashContrato.View {

    private val RC_LOGIN = 1
    private lateinit var presenter: SplashContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter = SplashPresenter(this)
        iniciarLogin()
    }

    private fun iniciarLogin() {
        Handler().postDelayed({ presenter.verificarLogin() }, 2000)
    }

    fun mostrarMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

    override fun logar() {
        startActivityForResult(Intent(this@SplashActivity, LoginActivity::class.java), RC_LOGIN)
    }

    override fun iniciarPrincipal() {
        //startActivity(this, ?::class.java)
        mostrarMensagem("PROXIMA TELA")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_LOGIN && resultCode == Activity.RESULT_OK) {
            presenter.completeLogin()
        } else {
            finish()
        }
    }
}
