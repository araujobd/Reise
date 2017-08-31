package com.gestao.reise.passageiro.splash

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.gestao.reise.reisecommon.source.Test

class Splash : Activity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        Test().insertPassageiro()
        //DataSourceImpl.buscarPassageiros { Toast.makeText(this, it.size, Toast.LENGTH_SHORT).show() }
    }
}
