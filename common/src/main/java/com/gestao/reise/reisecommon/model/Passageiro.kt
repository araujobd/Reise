package com.gestao.reise.reisecommon.model



/**
 * Created by bruno on 30/08/17.
 */

data class Passageiro(var uid: String,
                      var nome: String,
                      var fotoUrl: String,
                      var telefone: String,
                      var viagens: Map<String,Viagem>,
                      var endereco: String,
                      var descricao: String) {
    constructor() : this("", "", "", "", mapOf<String,Viagem>(), "", "")


    fun toMap():MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()

        map.put("uid", uid)
        map.put("nome", nome)
        map.put("telefone", telefone)
        map.put("endereco", endereco)
        map.put("descricao", descricao)
        if (fotoUrl.length > 0)
            map.put("fotoUrl", fotoUrl)

        return map
    }
}