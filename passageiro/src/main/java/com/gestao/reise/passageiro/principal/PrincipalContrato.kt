package com.gestao.reise.passageiro.principal

import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 07/09/17.
 */
interface PrincipalContrato {

    interface View {
        fun direcionarBusca()
        fun mostrarViagens(viagens: MutableList<Viagem>)
        fun mostrarNavHeader(nome: String, fotoUrl: String)
        fun sair()
    }

    interface Presenter {
        fun configNavHeader()
        fun possuiViagens()
        fun sair()
    }

}