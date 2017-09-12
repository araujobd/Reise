package com.gestao.reise.passageiro.perfil

import com.gestao.reise.passageiro.BasePresenter
import com.gestao.reise.passageiro.BaseView
import com.gestao.reise.reisecommon.model.Passageiro

/**
 * Created by bruno on 10/09/17.
 */
interface PerfilContrato {
    interface View : BaseView {
        fun mostrarPerfil(passageiro: Passageiro)

    }

    interface Presenter : BasePresenter {
        fun carregarPerfil()
        fun atualizarPerfil()
    }
}