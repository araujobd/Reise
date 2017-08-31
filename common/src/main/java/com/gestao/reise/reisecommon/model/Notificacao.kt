package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Notificacao(val timestamp: String,
                       val from: String,
                       val to: String,
                       val mensagem: String) {
    constructor() : this("", "", "", "")
}