package com.gestao.reise.reisecommon.model

import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by bruno on 30/08/17.
 */

data class Viagem(var uid: String,
                  var uid_frequencia: String,
                  var origem: String,
                  var destino: String,
                  var horario: String,
                  var data: String,
                  var preco: String,
                  var qtd_vagas: Int,
                  var frequencia: ArrayList<Boolean>,
                  var uid_mot: String) : Serializable{
    constructor() : this("", "", "","", "", "", "", -1, arrayListOf(), "")


    fun viagem_motorista():MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()

        map.put("qtd_vagas", qtd_vagas)
        map.put("uid", uid)
        map.put("origem", origem)
        map.put("destino", destino)
        map.put("preco", preco)
        map.put("horario", horario)
        map.put("data", data)

        return map
    }

    fun reserva():MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()

        map.put("uid", uid)
        map.put("origem", origem)
        map.put("destino", destino)
        map.put("preco", preco)
        map.put("data", data)
        map.put("horario", horario)
        map.put("uid_mot", uid_mot)

        return map
    }

}