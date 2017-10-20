package com.gestao.reise.reisecommon.source

import android.net.Uri
import android.util.Log
import com.gestao.reise.reisecommon.model.*
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener



/**
 * Created by bruno on 31/08/17.
 */

object DataSourceImpl : DataSource {

    private val database: FirebaseDatabase
    private val root: DatabaseReference

    init {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
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

    override fun salvarViagem(viagem: Viagem, uid_motorista: String) {
        val frequencia = Frequencia()
        frequencia.uid = root.child("frequencias").push().key
        viagem.uid_frequencia = frequencia.uid
        viagem.uid = root.child("viagens").push().key
        root.child("viagens").child(viagem.uid).setValue(viagem)
        root.child("frequencias").child(frequencia.uid).setValue(frequencia)
        root.child("motoristas").child(uid_motorista).child("viagens").child(viagem.uid).setValue(true)
    }

    override fun reservarViagem(dia: String,viagem: Viagem, uid_passageiro: String, sucesso: () -> Unit) {
        root.child("frequencias").child(viagem.uid_frequencia).child(dia).child(uid_passageiro).setValue(true)
        root.child("passageiros").child(uid_passageiro).child("viagens").child(viagem.uid).setValue(dia)
        sucesso()
    }

    override fun salvarCarro(carro: Carro) {
        carro.uid = root.child("carros").push().key
        root.child("carros").child(carro.uid).setValue(carro)
    }

    override fun buscarViagens(user: String, uid: String, action: (MutableList<Viagem>) -> Unit){
        val viagens: MutableList<Viagem> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("DATASSS", "Error")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot!!.children.forEach {
                    Log.i("logBusca", "data.key: " + it.key)
                    root.child("viagens").child(it.key).addValueEventListener(object : ValueEventListener {

                        override fun onCancelled(p0: DatabaseError?) {
                            Log.d("DATASSS", "Error")
                        }

                        override fun onDataChange(p0: DataSnapshot?) {
                            val viagem: Viagem? = p0!!.getValue(Viagem::class.java)
                            Log.i("logBusca", "viagem: " + viagem!!.origem + " / " + viagem!!.destino)
                            viagens.add(viagem)
                            action(viagens)
                        }

                    })
                }

            }
        }
        Log.i("logBusca",user+" "+uid)
        root.child(user).child(uid).child("viagens").addValueEventListener(listener)
    }

    override fun buscarViagensOD(origem: String, destino: String, action: (MutableList<Viagem>) -> Unit) {
        val viagens: MutableList<Viagem> = mutableListOf()

        val listener = object: ChildEventListener{
            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot?) {}
            override fun onCancelled(p0: DatabaseError?) {Log.d("DATASSS", "Error")}
            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val viagem: Viagem? = p0!!.getValue(Viagem::class.java)
                if (viagem!!.destino.equals(destino)) {
                    Log.i("logBusca", viagem.origem + viagem.destino)
                    viagens.add(viagem)
                }
                action(viagens)
            }
        }
        root.child("viagens").orderByChild("origem").equalTo(origem).addChildEventListener(listener)
        action(mutableListOf())
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
                if (passageiro != null)
                    sucesso(passageiro)
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


