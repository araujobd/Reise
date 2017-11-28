package com.gestao.reise.passageiro.detalhesViagem

import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 15/10/17.
 */
interface DetalhesContrato {
    interface view {
        fun msgSucesso()
        fun msgErro()
        fun dialogo(viagem: Viagem )
        fun devolveMotorista(mot: Motorista)
    }
    interface presenter {
        fun interesseVaga(viagem: Viagem)
        fun reservarVaga(viagem: Viagem)
        fun buscaMotorista(uid_mot: String)
    }
}