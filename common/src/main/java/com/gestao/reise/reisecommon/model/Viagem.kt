package com.gestao.reise.reisecommon.model

import java.util.*

/**
 * Created by bruno on 30/08/17.
 */

data class Viagem(var uid: String,
                  var origem: String,
                  var destino: String,
                  var horario: Date,
                  var preco: Double,
                  var qtd_vagas: Int,
                  var passageiros: MutableList<Passageiro>,
                  var motorista: Motorista) {
    constructor() : this("", "", "", Date(), (-1).toDouble(), -1,
            emptyList<Passageiro>() as MutableList<Passageiro>, Motorista())
}