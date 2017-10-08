package com.gestao.reise.passageiro.buscarViagem

import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 07/10/17.
 */
interface BuscarViagemContrato {

    interface View {
        fun mostrarMSG()
        fun listarViagens(viagens: MutableList<Viagem>)
    }

    interface Presenter {
        fun contemViagens(origem: String, destino: String)
    }

}