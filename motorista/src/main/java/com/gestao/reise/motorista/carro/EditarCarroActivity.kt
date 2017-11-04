package com.gestao.reise.motorista.carro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gestao.reise.motorista.R

class EditarCarroActivity : AppCompatActivity(), EditarCarroContrato.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_carro)
    }
}
