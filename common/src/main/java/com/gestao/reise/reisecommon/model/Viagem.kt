package com.gestao.reise.reisecommon.model

import java.util.*

/**
 * Created by bruno on 30/08/17.
 */

data class Viagem(val uid: String,
                  val origem: String,
                  val destino: String,
                  val horario: Date,
                  val preco: Double,
                  val qtd_vagas: Int,
                  val passageiros: MutableList<Passageiro>,
                  val motorista: Motorista)