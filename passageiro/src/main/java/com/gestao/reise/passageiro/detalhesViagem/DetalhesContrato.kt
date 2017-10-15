package com.gestao.reise.passageiro.detalhesViagem

import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 15/10/17.
 */
interface DetalhesContrato {
    interface view {
        fun msgSucesso()
    }
    interface presenter {
        fun reservarVaga(viagem: Viagem)
    }
}