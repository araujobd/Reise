package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Passageiro(var uid: String,
                      var nome: String,
                      var fotoUrl: String,
                      var telefone: String,
                      var viagens: MutableList<String>,
                      var endereco: String,
                      var descricao: String) {
    constructor() : this("", "", "", "", mutableListOf(), "", "")
}