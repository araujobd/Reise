package com.gestao.reise.reisecommon.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.gestao.reise.reisecommon.R
import com.gestao.reise.reisecommon.login.LoginActivity

/**
 * Created by bruno on 21/09/17.
 */

abstract class BaseSplashActivity : Activity(), SplashContrato.View {

    private val RC_LOGIN = 1
    abstract val presenter: SplashContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        configurarTela()
        esperar()
    }

    private fun configurarTela() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    private fun esperar() {
        Handler().postDelayed(
                { presenter.verificarLogin() },
                getString(R.string.splash_delay).toLong())
    }

    override fun logar() {
        startActivityForResult(Intent(this@BaseSplashActivity, LoginActivity::class.java), RC_LOGIN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_LOGIN && resultCode == Activity.RESULT_OK) {
            presenter.escolherAcao()
        } else {
            finish()
        }
    }

    fun mostrarMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }
}
