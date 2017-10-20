package com.gestao.reise.passageiro.detalhesViagem

import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 15/10/17.
 */
interface DetalhesContrato {
    interface view {
        fun msgSucesso()
        fun dialogo(viagem: Viagem,dia: String)
    }
    interface presenter {
        fun interesseVaga(viagem: Viagem,dia: String)
        fun reservarVaga(viagem: Viagem, dia: String)
    }
}