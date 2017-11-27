package com.gestao.reise.motorista.perfil

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.perfil.atualizar.EditarPerfilActivity
import com.gestao.reise.reisecommon.model.Motorista
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.content_perfil.*

class PerfilActivity : AppCompatActivity(), PerfilContrato.View {

    private val presenter: PerfilContrato.Presenter by lazy { PerfilPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        setActionBar(toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.title = ""
        presenter.carregarPerfil()

        configureButtons()
    }

    private fun configureButtons() {
        fab_editar.setOnClickListener {
            startActivity(Intent(this@PerfilActivity, EditarPerfilActivity::class.java))
        }

//        bt_call.setOnClickListener { mostrarMensagem(it, "CHAMANDO") }
    }

    override fun mostrarPerfil(motorista: Motorista) {
        Log.i("logBusca", "mostrarPerfil")
        Log.i("logBusca", motorista.toString())
        if (motorista.fotoUrl.length > 0)
            Picasso.with(this).load(motorista.fotoUrl).noFade().into(img_perfil)
        Log.i("logBusca", "foto")
        tv_nome.setText(motorista.nome)
        tv_telefone.setText(motorista.telefone)
        tv_endereco.setText(motorista.endereco)
        tv_descricao.setText(motorista.descricao)
    }
}
