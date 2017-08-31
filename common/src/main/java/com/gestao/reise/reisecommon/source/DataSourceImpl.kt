package com.gestao.reise.reisecommon.source

import com.gestao.reise.reisecommon.model.Carro
import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.model.Viagem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by bruno on 31/08/17.
 */

object DataSourceImpl : DataSource  {

    private val database: FirebaseDatabase
    private val root: DatabaseReference

    init {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        database = FirebaseDatabase.getInstance()
        root = database.reference
        root.keepSynced(true)
    }

    override fun salvarMotorista(uid: String, motorista: Motorista) {
        root.child("motoristas").child(uid).setValue(motorista)
    }

    override fun salvarPassageiro(uid: String, passageiro: Passageiro) {
        root.child("passageiros").child(uid).setValue(passageiro)
    }

    override fun salvarViagem(uid: String, viagem: Viagem) {
        root.child("viagens").child(uid).setValue(viagem)
    }

    override fun salvarCarro(uid: String, carro: Carro) {
        root.child("carros").child(uid).setValue(carro)
    }

}