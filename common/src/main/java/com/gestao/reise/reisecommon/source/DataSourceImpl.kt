package com.gestao.reise.reisecommon.source

import android.net.Uri
import android.util.Log
import com.gestao.reise.reisecommon.model.Carro
import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.model.Viagem
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

/**
 * Created by bruno on 31/08/17.
 */

object DataSourceImpl : DataSource {

    private val database: FirebaseDatabase
    private val root: DatabaseReference

    init {
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        database = FirebaseDatabase.getInstance()
        root = database.reference
        root.keepSynced(true)
    }

    override fun salvarMotorista(motorista: Motorista) {
        //motorista.uid = root.child("motoristas").push().key
        root.child("motoristas").child(motorista.uid).setValue(motorista)
    }

    override fun salvarPassageiro(passageiro: Passageiro) {
        //root.child("passageiros").child(passageiro.uid).setValue(passageiro)
        root.child("passageiros").child(passageiro.uid).updateChildren(passageiro.toMap())
    }

    override fun salvarViagem(viagem: Viagem) {
        viagem.uid = root.child("viagens").push().key
        root.child("viagens").child(viagem.uid).setValue(viagem)
    }

    override fun salvarCarro(carro: Carro) {
        carro.uid = root.child("carros").push().key
        root.child("carros").child(carro.uid).setValue(carro)
    }

    override fun buscarViagens(user: String, uid: String, action: (MutableList<Viagem>) -> Unit) {
        val viagens: MutableList<Viagem> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("DATASSS", "Error")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach {
                    val viagem: Viagem? = it.getValue(Viagem::class.java)
                    if (viagem != null) {
                        viagens.add(viagem)
                    }
                }
                if(user.equals("motorista"))
                    action(viagens)
                else if(user.equals("passageiro"))
                    action(viagens)
            }
        }

        root.child(user).orderByChild(uid).orderByChild("viagens").addValueEventListener(listener)
    }

    override fun buscarPassageiros(callback: (MutableList<Passageiro>) -> Unit) {
        val passageiros: MutableList<Passageiro> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("DATASSS", "Error")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach {
                    val passageiro: Passageiro? = it.getValue(Passageiro::class.java)
                    if (passageiro != null) {
                        passageiros.add(passageiro)
                    }
                }
                callback(passageiros)
            }
        }

        root.child("passageiros").addValueEventListener(listener)
    }

    override fun buscarUidUser(typeUser: String, uid: String, sucesso: () -> Unit, erro: () -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("DATASSS", p0.toString())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                if ( dataSnapshot?.value != null)
                    sucesso()
                else
                    erro()
            }
        }
        //                     Busca exata pelo Uid
        root.child(typeUser).orderByKey().equalTo(uid).addListenerForSingleValueEvent(listener)

    }

    override fun buscarMotorista(uid: String, sucesso: (motorista: Motorista) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val motorista: Motorista? = dataSnapshot?.getValue(Motorista::class.java)
                motorista?.let { sucesso(it) }
            }

        }
        root.child("motoristas").child(uid).addValueEventListener(listener)
    }

    override fun buscarPassageiro(uid: String, sucesso: (passageiro: Passageiro) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val passageiro: Passageiro? = dataSnapshot?.getValue(Passageiro::class.java)
                passageiro?.let { sucesso(it) }
            }

        }
        root.child("passageiros").child(uid).addValueEventListener(listener)
    }

    override fun salvarImagem(uid: String, imagePath: Uri?, sucesso: (String) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference.child("images")
        val upload = storageRef.child(uid).child("profile").putFile(imagePath!!)

       upload.addOnCompleteListener { sucesso(it.result.downloadUrl.toString()) }
    }

}

