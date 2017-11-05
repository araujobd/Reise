package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Carro(var uid: String,
                 var placa: String,
                 var modelo: String,
                 var cor: String,
                 var qtd_vagas: Int,
                 var vaga_crianca: Boolean) {
    constructor() : this("", "", "", "", -1, false)
}


