package com.gestao.reise.reisecommon.source

import com.gestao.reise.reisecommon.model.Passageiro

/**
 * Created by bruno on 05/11/17.
 */
interface NewDataSource {

    fun salvarPassageiro(passageiro: Passageiro)
}