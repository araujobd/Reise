package com.gestao.reise.motorista.detalhesPassageiros

import com.gestao.reise.reisecommon.model.Passageiro

/**
 * Created by cainan on 30/10/17.
 */
interface DetalhesPassageirosContrato {

    interface view {
        fun mostrarPassageiros(passageiros: MutableList<Passageiro>)
    }

    interface presenter{
        fun buscarPassageiros(uid_via: String)
    }

}