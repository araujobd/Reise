package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Motorista(var telefone: String,
                     var endereco: String,
                     val carro: Carro) {
    constructor() : this("", "", Carro())
}

