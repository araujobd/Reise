package com.gestao.reise.reisecommon.model

/**
 * Created by bruno on 30/08/17.
 */

data class Notificacao(var uid: String,
                       var timestamp: String,
                       var from: String,
                       var to: String,
                       var mensagem: String) {
    constructor() : this("", "", "", "", "")
}