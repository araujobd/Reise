package com.gestao.reise.motorista.cadastrarViagem

import android.util.Log
import com.gestao.reise.reisecommon.model.Viagem
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by cainan on 20/09/17.
 */
class CadastrarViagemPresenter(val view: CadastrarViagemContrato.view) : CadastrarViagemContrato.presenter{

    private val source: DataSource = DataSourceImpl
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun cadastrarViagem(origem : String, destino : String, preco : String, horario: String, data: String//,frequencia: ArrayList<Boolean>
    ) {
        val uid_motorista = auth.currentUser?.uid!!
        var viagem = Viagem()
        source.buscarMotorista(uid_motorista) { motorista ->
            viagem.qtd_vagas = motorista.carro.qtd_vagas
            viagem.origem = origem
            viagem.destino = destino
            viagem.preco = preco
            viagem.horario = horario
            viagem.data = data
            viagem.uid_mot = uid_motorista
            source.salvarViagem(viagem,uid_motorista)
            view.msgSucesso()
        }

    }

}