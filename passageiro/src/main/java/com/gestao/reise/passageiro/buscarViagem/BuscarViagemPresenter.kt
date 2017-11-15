package com.gestao.reise.passageiro.buscarViagem

import android.util.Log
import com.gestao.reise.reisecommon.model.Viagem
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl

/**
 * Created by cainan on 07/10/17.
 */
class BuscarViagemPresenter(val view: BuscarViagemContrato.View): BuscarViagemContrato.Presenter {

    private var source: DataSource = DataSourceImpl

    override fun contemViagens(origem: String, destino: String) {
        val entrada = validarEntrada(origem,destino)
        if(entrada == 3)
            source.buscarViagensPorOrigemDestino(origem,destino){viagens ->
                if(viagens.isNotEmpty())
                    view.listarViagens(viagens)
                else
                    erroBusca()
            }
        else if(entrada == 1)
            source.buscarViagensPorOrigem(origem){viagens ->
                if(viagens.isNotEmpty())
                    view.listarViagens(viagens)
                else
                    erroBusca()
            }
        else if(entrada == 2)
            source.buscarViagensPorDestino(destino){viagens ->
                if(viagens.isNotEmpty())
                    view.listarViagens(viagens)
                else
                    erroBusca()
            }
        else
            erroBusca()
    }
    fun erroBusca(){
        view.mostrarMSG()
        view.listarViagens(mutableListOf())
    }
    fun validarEntrada(origem: String, destino: String): Int {
        if(origem.isNotBlank() && destino.isNotBlank())
            return 3
        else if(origem.isNotBlank())
            return 1
        else if(destino.isNotBlank())
            return 2
        return 0
    }
}