package com.gestao.reise.motorista.principal

import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 07/09/17.
 */
interface PrincipalContrato {

    interface View {
        fun direcionarCadastro()
        fun mostrarViagens(viagens: (MutableList<Viagem>))
        fun sair()
    }

    interface Presenter {
        fun possuiViagens()
        fun sair()
    }

}