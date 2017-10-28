package com.gestao.reise.reisecommon.model

import java.io.Serializable

/**
 * Created by cainan on 17/10/17.
 */
data class Frequencia(var uid: String,
                      var dom: MutableList<String>,
                      var seg: MutableList<String>,
                      var ter: MutableList<String>,
                      var qua: MutableList<String>,
                      var qui: MutableList<String>,
                      var sex: MutableList<String>,
                      var sab: MutableList<String>) {
    constructor() : this(
                "",
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf()
        )
}