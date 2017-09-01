package com.gestao.reise.passageiro.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.login.LoginActivity

class Splash : Activity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        iniciarLogin()
    }

    private fun iniciarLogin() {
        Handler().postDelayed({ startActivity( Intent(this@Splash, LoginActivity::class.java))}, 2000)
    }

    fun mostrarMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
