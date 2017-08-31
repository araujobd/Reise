package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Carro(val placa: String,
                 val modelo: String,
                 val cor: String,
                 val qtd_vagas: Int,
                 val vaga_crianca: Int) {
    constructor() : this("", "", "", -1, -1)
}


