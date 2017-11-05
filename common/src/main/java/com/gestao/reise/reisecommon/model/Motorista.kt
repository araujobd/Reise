package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Motorista(var uid: String,
                     var nome: String,
                     var fotoUrl: String,
                     var telefone: String,
                     var viagens: Map<String,Boolean>,
                     var endereco: String,
                     var descricao: String,
                     var carro: Carro) {
    constructor() : this("", "", "", "", mapOf<String,Boolean>(), "", "", Carro())

    fun toMap(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()

        map.put("uid", uid)
        map.put("nome", nome)
        map.put("telefone", telefone)
        map.put("viagens", viagens)
        map.put("endereco", endereco)
        map.put("descricao", descricao)
        map.put("carro", carro)
        if (fotoUrl.length > 0)
            map.put("fotoUrl", fotoUrl)

        return map
    }
}

