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

    private lateinit var database: FirebaseDatabase
    private lateinit var root: DatabaseReference

    init {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        database = FirebaseDatabase.getInstance()
        root = database.reference
        root.keepSynced(true)
    }

    override fun salvarMotorista(uid: String, motorista: Motorista) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun salvarPassageiro(uid: String, passageiro: Passageiro) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun salvarViagem(uid: String, viagem: Viagem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun salvarCarro(uid: String, carro: Carro) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}