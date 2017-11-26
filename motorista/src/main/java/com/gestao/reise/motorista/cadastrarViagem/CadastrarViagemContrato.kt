package com.gestao.reise.motorista.cadastrarViagem

import com.gestao.reise.motorista.BasePresenter
import com.gestao.reise.motorista.BaseView
import java.util.*

/**
 * Created by cainan on 20/09/17.
 */
interface CadastrarViagemContrato {
    interface view{
        fun msgSucesso()
    }
    interface presenter{
        fun cadastrarViagem(origem : String, destino : String, preco : String, horario: String,  data: String//, frequencia: ArrayList<Boolean>
        )
    }
}