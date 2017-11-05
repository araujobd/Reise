package com.gestao.reise.motorista.carro

/**
 * Created by bruno on 05/10/17.
 */
interface EditarCarroContrato {

    interface View {
        fun done()
    }

    interface Presenter {
        fun cadastrarCarro(cor: String, modelo: String, placa: String, qtd_vagas: Int, vaga_crianca: Boolean)
    }
}