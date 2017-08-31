package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Motorista(var uid: String,
                     var nome: String,
                     var telefone: String,
                     var endereco: String,
                     var carro: Carro) {
    constructor() : this("", "", "", "", Carro())
}

