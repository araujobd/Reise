package com.gestao.reise.motorista.carro

import com.gestao.reise.reisecommon.model.Carro

/**
 * Created by bruno on 05/10/17.
 */
interface EditarCarroContrato {

    interface View {
        fun done()
        fun entregarCarro(carro: Carro)
    }

    interface Presenter {
        fun cadastrarCarro(cor: String, modelo: String, placa: String, qtd_vagas: Int, vaga_crianca: Boolean)
        fun carregarCarro()
    }
}