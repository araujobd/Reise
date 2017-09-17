package com.gestao.reise.passageiro.primeiroLogin

import android.net.Uri
import com.gestao.reise.passageiro.BasePresenter
import com.gestao.reise.passageiro.BaseView
import com.gestao.reise.reisecommon.model.Passageiro

/**
 * Created by bruno on 13/09/17.
 */
interface CompletarPerfilContrato {

    interface View : BaseView {
        fun mostrarPerfil(passageiro: Passageiro)
    }

    interface Presenter : BasePresenter {
        fun carregarPerfil()
        fun atualizarPerfil(imagePath: Uri?, nome: String, celular: String, endereco: String, descricao: String)
    }
}