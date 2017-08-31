package com.gestao.reise.reisecommon.source

import com.gestao.reise.reisecommon.model.Carro
import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.model.Viagem
import com.google.firebase.database.*

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

    override fun salvarMotorista(motorista: Motorista) {
        motorista.uid = root.child("motoristas").push().key
        root.child("motoristas").child(motorista.uid).setValue(motorista)
    }

    override fun salvarPassageiro(passageiro: Passageiro) {
        passageiro.uid = root.child("passgeiros").push().key
        root.child("passageiros").child(passageiro.uid).setValue(passageiro)
    }

    override fun salvarViagem(viagem: Viagem) {
        viagem.uid = root.child("viagens").push().key
        root.child("viagens").child(viagem.uid).setValue(viagem)
    }

    override fun salvarCarro(carro: Carro) {
        carro.uid = root.child("carros").push().key
        root.child("carros").child(carro.uid).setValue(carro)
    }

    override fun buscarPassageiros(callback: (MutableList<Passageiro>) -> Unit) {
        val passageiros: MutableList<Passageiro> = mutableListOf()
        root.child("passageiros").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                //for (data: DataSnapshot in dataSnapshot?.children!!) {
                //    val passageiro: Passageiro = data.getValue(Passageiro::class.java)
                //    passageiros.add(passageiro)
                //}
                dataSnapshot?.children?.forEach {
                    val passageiro: Passageiro = it.getValue(Passageiro::class.java)
                    passageiros.add(passageiro)
                }
            }

        })
        callback(passageiros)
    }

}