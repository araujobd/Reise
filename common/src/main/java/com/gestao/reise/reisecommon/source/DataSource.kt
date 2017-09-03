package com.gestao.reise.reisecommon.source

import com.gestao.reise.reisecommon.model.Carro
import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by bruno on 31/08/17.
 */
interface DataSource {
    fun salvarMotorista(motorista: Motorista)
    fun salvarPassageiro(passageiro: Passageiro)
    fun salvarViagem(viagem: Viagem)
    fun salvarCarro(carro: Carro)

    fun buscarUidPassageiro(uid: String): Boolean
    fun buscarPassageiros(callback: (MutableList<Passageiro>) -> Unit)
}