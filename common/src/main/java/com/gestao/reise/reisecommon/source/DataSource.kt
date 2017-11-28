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
    fun salvarCarro(uid_motorista: String, carro: Carro)
    fun removerCard(uid_user: String, uid_viagem: String,uid_freq: String,dia: String)
    fun reservarViagem(dia: String,viagem: Viagem,uid_passageiro: String, sucesso: () -> Unit)

    fun buscarUidUser(typeUser: String, uid: String, sucesso: () -> Unit, erro: () -> Unit)
    fun buscarPassageiro(uid: String, sucesso: (passageiro: Passageiro) -> Unit)
    fun buscarViagens(user: String, uid: String, action: (MutableList<Viagem>) -> Unit)
    fun buscarDia(uid: String,action: (String) -> Unit)
    fun buscarViagensOD(origem: String, destino: String, action: (MutableList<Viagem>) -> Unit)
    fun buscarFrequencia(uid_freq: String, dia: String, action: (MutableList<Passageiro>) -> Unit)

    fun salvarImagem(type: String, uid: String, imagePath : Uri?, sucesso: (String) -> Unit)


    /* ********************* ********************/

    fun navBusca(uid: String, type: String, sucesso: (nome: String, fotoUrl: String) -> Unit)
    fun buscarVagas(uid_via: String, sucesso: (vagas: Int) -> Unit)
    fun buscarPerfilPassageiro(uid: String, sucesso: (passageiro: Passageiro) -> Unit)
    fun buscarPerfilMotorista(uid: String, sucesso: (motorista: Motorista) -> Unit)
    fun buscarViagensPorPassageiro(uid: String, sucesso: (viagens: MutableList<Viagem>) -> Unit)
    fun buscarViagensPorMotorista(uid: String, sucesso: (viagens: MutableList<Viagem>) -> Unit)
    fun buscarDetalhesBuscaViagem(uid: String, sucesso: (viagem: Viagem) -> Unit)
    fun buscarDetalhesViagemMotorista(uid: String, sucesso: (viagem: Viagem) -> Unit)
    fun buscarViagensPorOrigem(origem: String, action: (MutableList<Viagem>) -> Unit)
    fun buscarViagensPorDestino(destino: String, action: (MutableList<Viagem>) -> Unit)
    fun buscarViagensPorOrigemDestino(origem: String,destino: String, action: (MutableList<Viagem>) -> Unit)
    fun buscarPassageiros(uid_via: String, callback: (MutableList<Passageiro>) -> Unit)
    fun buscarMotorista(uid: String, sucesso: (motorista: Motorista) -> Unit)
    fun buscarCarro(uid_mot: String, sucesso: (carro: Carro) -> Unit)
    fun reservar(viagem: Viagem, vagas: Int, passageiro: Passageiro, sucesso: () -> Unit)
    fun removerViagemMotorista(uid_mot: String, uid_via: String, sucesso: () -> Unit)
    //fun removerCardMotorista()
}