package com.gestao.reise.passageiro.detalhesViagem

import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.model.Viagem
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by cainan on 15/10/17.
 */
class DetalhesPresenter(val view: DetalhesContrato.view) : DetalhesContrato.presenter{

    private val source: DataSource = DataSourceImpl
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun interesseVaga(viagem: Viagem) {
        view.dialogo(viagem)
    }

    override fun buscaMotorista(uid_mot: String) {
        source.buscarMotorista(uid_mot){motorista ->
            view.devolveMotorista(motorista)
        }
    }


    override fun reservarVaga(viagem: Viagem) {
        if(viagem.qtd_vagas > 0)
            source.buscarPassageiro(auth.currentUser!!.uid,
                sucesso = { passageiro -> source.reservar(viagem.uid,(viagem.qtd_vagas - 1),passageiro,
                                             sucesso = {view.msgSucesso()})})
        else
            view.msgErro()

    }
}