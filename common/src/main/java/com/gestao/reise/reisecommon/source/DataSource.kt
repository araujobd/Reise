package com.gestao.reise.reisecommon.source

import com.gestao.reise.reisecommon.model.Carro
import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by bruno on 31/08/17.
 */
interface DataSource {
    fun salvarMotorista(uid: String, motorista: Motorista)
    fun salvarPassageiro(uid: String, passageiro: Passageiro)
    fun salvarViagem(uid: String, viagem: Viagem)
    fun salvarCarro(uid: String, carro: Carro)
}