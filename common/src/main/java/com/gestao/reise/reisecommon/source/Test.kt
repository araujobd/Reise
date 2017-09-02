package com.gestao.reise.reisecommon.source

import com.gestao.reise.reisecommon.model.Passageiro

/**
 * Created by bruno on 31/08/17.
 */

class Test {
    private lateinit var source: DataSource

    init {
        source = DataSourceImpl
    }

    fun insertPassageiro() {
        var passageiro = Passageiro("", "Cainan","98993-4343","Ruasdjksah akj ashdl")
        source.salvarPassageiro(passageiro)
    }

    fun buscarPassageiros() : MutableList<Passageiro>  {
        var list: MutableList<Passageiro> = mutableListOf()
        source.buscarPassageiros { list.addAll(it) }
        return list
    }
}