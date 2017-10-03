package com.gestao.reise.reisecommon.source

import android.net.Uri
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
    fun salvarViagem(viagem: Viagem,uid_motorista: String)
    fun salvarCarro(carro: Carro)

    fun buscarUidUser(typeUser: String, uid: String, sucesso: () -> Unit, erro: () -> Unit)
    fun buscarPassageiros(callback: (MutableList<Passageiro>) -> Unit)

    fun buscarMotorista(uid: String, sucesso: (motorista: Motorista) -> Unit)
    fun buscarPassageiro(uid: String, sucesso: (passageiro: Passageiro) -> Unit)
    fun buscarViagens(user: String, uid: String, action: (MutableList<Viagem>) -> Unit)

    fun salvarImagem(uid: String, imagePath : Uri?, sucesso: (String) -> Unit)
}