package com.gestao.reise.passageiro.principal

import android.os.Bundle
import com.gestao.reise.passageiro.BaseActivity

import com.gestao.reise.passageiro.R

class PrincipalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        showProgress()
    }
}
