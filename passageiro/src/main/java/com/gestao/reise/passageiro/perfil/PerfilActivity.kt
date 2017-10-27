package com.gestao.reise.passageiro.perfil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.gestao.reise.passageiro.BaseActivity

import com.gestao.reise.passageiro.R
import com.gestao.reise.passageiro.primeiroLogin.CompletarPerfilActivity
import com.gestao.reise.reisecommon.model.Passageiro
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.content_perfil.*

class PerfilActivity : BaseActivity(), PerfilContrato.View {

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
            startActivity(Intent(this@PerfilActivity, CompletarPerfilActivity::class.java))
        }

        bt_call.setOnClickListener { mostrarMensagem(it, "CHAMANDO") }
    }

    override fun mostrarPerfil(passageiro: Passageiro) {
        Log.i("logBusca", "mostrarPerfil")
        Picasso.with(this).load(passageiro.fotoUrl).noFade().into(img_perfil)
        Log.i("logBusca", "foto")
        tv_nome.setText(passageiro.nome)
        tv_telefone.setText(passageiro.telefone)
        tv_endereco.setText(passageiro.endereco)
        tv_descricao.setText(passageiro.descricao)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> { finish() }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}
