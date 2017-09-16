package com.gestao.reise.passageiro.primeiroLogin

import android.os.Bundle
import com.gestao.reise.passageiro.BaseActivity

import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Passageiro
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_completar_perfil.*

class CompletarPerfilActivity : BaseActivity(), CompletarPerfilContrato.View {

    private val presenter: CompletarPerfilContrato.Presenter by lazy { CompletarPerfilPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completar_perfil)

        configurarTela()
    }

    private fun configurarTela() {
        presenter.carregarPerfil()
    }

    override fun mostrarPerfil(passageiro: Passageiro) {
        ed_nome.setText(passageiro.nome)
        ed_celular.setText(passageiro.telefone)
        ed_descricao.setText(passageiro.descricao)
        ed_endereco.setText(passageiro.endereco)
        Picasso.with(this).load(passageiro.fotoUrl).into(img_perfil)
    }
}
