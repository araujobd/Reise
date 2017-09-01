package com.gestao.reise.passageiro.splash

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.source.DA
import com.gestao.reise.reisecommon.source.DataSourceImpl

class Splash : Activity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DataSourceImpl.buscarPassageiros{ mostrarMensagem(it.size.toString())}
    }

    fun mostrarMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
