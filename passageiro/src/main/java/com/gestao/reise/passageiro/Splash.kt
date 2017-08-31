package com.gestao.reise.passageiro

import android.app.Activity
import android.os.Bundle
import com.gestao.reise.reisecommon.source.Test

class Splash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        Test().insertPassageiro()
    }
}
