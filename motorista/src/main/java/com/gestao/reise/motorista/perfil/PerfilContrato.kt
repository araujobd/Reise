package com.gestao.reise.motorista.perfil

import com.gestao.reise.motorista.BasePresenter
import com.gestao.reise.motorista.BaseView
import com.gestao.reise.reisecommon.model.Motorista

/**
 * Created by bruno on 13/09/17.
 */
interface PerfilContrato {
    interface View : BaseView {
        fun mostrarPerfil(motorista: Motorista)

    }

    interface Presenter : BasePresenter {
        fun carregarPerfil()
    }
}