package com.gestao.reise.passageiro.buscarViagem

import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl

/**
 * Created by cainan on 07/10/17.
 */
class BuscarViagemPresenter(val view: BuscarViagemContrato.View): BuscarViagemContrato.Presenter {

    private var source: DataSource = DataSourceImpl

    override fun contemViagens(origem: String, destino: String) {
        source.buscarViagensOD(origem,destino){viagens ->
            if(viagens.isEmpty()){
                view.mostrarMSG()
            }else{
                view.listarViagens(viagens)
            }
        }
    }
}