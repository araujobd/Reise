package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Passageiro(var telefone: String,
                      var endereco: String) {
    constructor() : this("", "")
}