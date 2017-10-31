package com.gestao.reise.motorista.detalhesPassageiros

import android.util.Log
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by cainan on 30/10/17.
 */
class DetalhesPassageirosPresenter(val view: DetalhesPassageirosContrato.view): DetalhesPassageirosContrato.presenter {

    private val source: DataSource = DataSourceImpl
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun buscarFrequencia(freq: Array<String>) {
        source.buscarFrequencia(freq[0],freq[1]){
            passageiros: MutableList<Passageiro> ->
            Log.i("logBusca",""+passageiros)
            view.mostrarPassageiros(passageiros)
        }
    }

}