package com.gestao.reise.reisecommon.source

import com.gestao.reise.reisecommon.model.Passageiro
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by bruno on 05/11/17.
 */
object Implementacao : NewDataSource {

    private val database: FirebaseDatabase
    private val root: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance()
        root = database.reference
    }

    override fun salvarPassageiro(passageiro: Passageiro) {
        root.child("users_passageiros").child(passageiro.uid).updateChildren(passageiro.toMap())
    }
}