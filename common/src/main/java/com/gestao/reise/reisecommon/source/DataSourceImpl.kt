package com.gestao.reise.reisecommon.source

import android.util.Log
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
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true)
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
        Log.d("DATASSS", "O" + root)

        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("DATASSS", "ASASADSA")
            }

            override fun onDataChange(p0: DataSnapshot?) {
                Log.d("DATASSS", "ASASADSA" + p0)
            }

        }
        root.child("passageiros").addValueEventListener(listener)
        root.child("passageiros").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("DATASSS", "ERRO" + p0)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                Log.d("DATASSS", "1")
                //for (data: DataSnapshot in dataSnapshot?.children!!) {
                //    val passageiro: Passageiro = data.getValue(Passageiro::class.java)
                //    passageiros.add(passageiro)
                //}
               // dataSnapshot?.children?.forEach {
                //    val passageiro: Passageiro = it.getValue(Passageiro::class.java)
                  //  passageiros.add(passageiro)
                //}
                Log.d("DATASSS", "2")
            }

        })
        Log.d("DATASSS", "3 " + passageiros)
        //callback(passageiros)
        Log.d("DATASSS", "4")
    }

}