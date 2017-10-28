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
                  var preco: String,
                  var qtd_vagas: Int,
                  var frequencia: ArrayList<Boolean>,
                  var motorista: String) : Serializable{
    constructor() : this("", "", "", "", "", "", -1, arrayListOf(), "")
}