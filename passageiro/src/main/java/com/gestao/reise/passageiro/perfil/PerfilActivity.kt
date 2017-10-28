package com.gestao.reise.passageiro.perfil

import android.content.Intent
import android.os.Bundle
import com.gestao.reise.passageiro.BaseActivity
import com.gestao.reise.passageiro.R
import com.gestao.reise.passageiro.perfil.editar.EditarPerfilActivity
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
        actionBar.title = ""
        presenter.carregarPerfil()

        configureButtons()
    }

    private fun configureButtons() {
        fab_editar.setOnClickListener {
            startActivity(Intent(this@PerfilActivity, EditarPerfilActivity::class.java))
        }

        bt_call.setOnClickListener { mostrarMensagem(it, "CHAMANDO") }
    }

    override fun mostrarPerfil(passageiro: Passageiro) {
        Picasso.with(this).load(passageiro.fotoUrl).noFade().into(img_perfil)
        tv_nome.setText(passageiro.nome)
        tv_telefone.setText(passageiro.telefone)
        tv_endereco.setText(passageiro.endereco)
        tv_descricao.setText(passageiro.descricao)
    }
}
