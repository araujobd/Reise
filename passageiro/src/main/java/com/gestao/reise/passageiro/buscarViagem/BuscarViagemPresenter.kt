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
        if(validarEntrada(origem,destino))
            source.buscarViagensOD(origem,destino){viagens ->
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
    fun validarEntrada(origem: String, destino: String): Boolean {
        if(origem.isBlank()){
            return false
        }
        return true
    }
}