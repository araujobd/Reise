package com.gestao.reise.motorista

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.gestao.reise.reisecommon.source.DataSourceImpl

class Splash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       // DataSourceImpl.buscarPassageiros { Toast.makeText(this, it.size.toString(), Toast.LENGTH_SHORT).show() }
    }
}
