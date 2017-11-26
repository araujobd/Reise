package com.gestao.reise.reisecommon.source

import android.net.Uri
import android.util.Log
import com.gestao.reise.reisecommon.model.*
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

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
        root.child("users/motoristas").child(motorista.uid).updateChildren(motorista.toMap())
    }

    override fun salvarPassageiro(passageiro: Passageiro) {
        root.child("users/passageiro").child(passageiro.uid).updateChildren(passageiro.toMap())
    }

    override fun salvarViagem(viagem: Viagem, uid_motorista: String) {
        viagem.uid = root.child("viagens").push().key
        root.child("viagens").child(viagem.uid).setValue(viagem)
    }

    override fun removerCard(uid_user: String, uid_viagem: String,uid_freq: String,dia: String) {
        buscarVagas(uid_viagem){vagas ->
            val qtdVagas = vagas + 1
            root.child("viagens").child(uid_viagem).child("qtd_vagas").setValue(qtdVagas)
            root.child("viagens").child(uid_viagem).child("passageiros").child(uid_user).removeValue()
            root.child("viagens_passageiro_principal").child(uid_user).child(uid_viagem).removeValue()
        }

    }

    override fun buscarVagas(uid_via: String, sucesso: (vagas: Int) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val vagas = dataSnapshot?.value.toString().toInt()
                sucesso(vagas)
            }
        }
        root.child("viagens").child(uid_via).child("qtd_vagas").addListenerForSingleValueEvent(listener)
    }

    override fun reservarViagem(dia: String,viagem: Viagem, uid_passageiro: String, sucesso: () -> Unit) {
        root.child("frequencias").child(viagem.uid_frequencia).child(dia).child(uid_passageiro).setValue(true)
        root.child("passageiros").child(uid_passageiro).child("viagens").child(viagem.uid).child(dia).setValue(true)
        sucesso()
    }

    override fun salvarCarro(uid_motorista: String, carro: Carro) {
        carro.uid = root.child("users/motoristas").child(uid_motorista).child("carro").push().key
        root.child("users/motoristas").child(uid_motorista).child("carro").setValue(carro)
    }

    override fun buscarDia(uid: String, action: (String) -> Unit) {
        //root.child()
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
                            Log.i("logBuscaa",p0.toString())
                            val viagem: Viagem? = p0!!.getValue(Viagem::class.java)
                            Log.i("logBusca", "viagem: " + viagem!!.origem + " / " + viagem.destino)
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
                if ((viagem!!.destino.equals(destino)) or destino.isBlank()) {
                    Log.i("logBusca", viagem.origem + viagem.destino)
                    viagens.add(viagem)
                    action(viagens)
                }
            }
        }
        root.child("viagens").orderByChild("origem").equalTo(origem).addChildEventListener(listener)
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
        //root.child(typeUser).orderByKey().equalTo(uid).addListenerForSingleValueEvent(listener)
        root.child("users/passageiro").orderByKey().equalTo(uid).addListenerForSingleValueEvent(listener)

    }



    override fun buscarPassageiro(uid: String, sucesso: (passageiro: Passageiro) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val passageiro: Passageiro? = dataSnapshot?.getValue(Passageiro::class.java)
                sucesso(passageiro as Passageiro)
            }
        }
        root.child("users/passageiro").child(uid).addValueEventListener(listener)
    }

    override fun buscarFrequencia(uid_freq: String, dia: String, action: (MutableList<Passageiro>) -> Unit) {
        val passageiros: MutableList<Passageiro> = mutableListOf()
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach {
                    Log.i("logBusca",dataSnapshot.toString())
                    buscarPassageiro(it.key){
                        passageiro ->  passageiros.add(passageiro)
                        Log.i("logBusca", "Buscou: "+passageiro)
                        action(passageiros)
                    }
                }
            }
        }
        root.child("frequencias").child(uid_freq).child(dia).addValueEventListener(listener)
    }

    override fun salvarImagem(uid: String, imagePath: Uri?, sucesso: (String) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference.child("images")
        val upload = storageRef.child(uid).child("profile").putFile(imagePath!!)

       upload.addOnCompleteListener { sucesso(it.result.downloadUrl.toString()) }
    }

    /* *************************************************************************** */



    override fun navBusca(uid: String, type: String, sucesso: (nome: String, fotoUrl: String) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val nome = dataSnapshot?.child("nome")?.value.toString()
                val fotoUrl = dataSnapshot?.child("fotoUrl")?.value.toString()
                sucesso(nome, fotoUrl)
            }
        }
        root.child("nav").child(type).child(uid).addValueEventListener(listener)
    }

    override fun buscarPerfilPassageiro(uid: String, sucesso: (passageiro: Passageiro) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val passageiro: Passageiro? = dataSnapshot?.getValue(Passageiro::class.java)
                passageiro?.let(sucesso)
            }
        }
        root.child("perfil/passageiro").child(uid).addValueEventListener(listener)
    }

    override fun buscarPerfilMotorista(uid: String, sucesso: (motorista: Motorista) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val motorista: Motorista? = dataSnapshot?.getValue(Motorista::class.java)
                motorista?.let(sucesso)
            }
        }
        root.child("perfil/motorista").child(uid).addValueEventListener(listener)
    }

    override fun buscarViagensPorPassageiro(uid: String, sucesso: (viagens: MutableList<Viagem>) -> Unit) {
        val viagens = mutableListOf<Viagem>()
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach {
                    val viagem: Viagem? = it.getValue(Viagem::class.java)
                    viagem?.let { viagens.add(it) }
                }
                sucesso(viagens)
            }
        }
        root.child("viagens_passageiro_principal").child(uid).orderByChild("data").addValueEventListener(listener)
    }

    override fun buscarViagensPorMotorista(uid: String, sucesso: (viagens: MutableList<Viagem>) -> Unit) {
        val viagens = mutableListOf<Viagem>()
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach {
                    val viagem: Viagem? = it.getValue(Viagem::class.java)
                    viagem?.let { viagens.add(it) }
                }
                sucesso(viagens)
            }
        }
        root.child("viagem_motorista").child(uid).orderByChild("data").addValueEventListener(listener)
    }

    override fun buscarDetalhesBuscaViagem(uid: String, sucesso: (viagem: Viagem) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val viagem: Viagem? = dataSnapshot?.getValue(Viagem::class.java)
                viagem?.let(sucesso)
            }
        }
        root.child("detalhes_viagem_busca").child(uid).addValueEventListener(listener)
    }

    override fun buscarDetalhesViagemMotorista(uid: String, sucesso: (viagem: Viagem) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val viagem: Viagem? = dataSnapshot?.getValue(Viagem::class.java)
                viagem?.let(sucesso)
            }
        }
        root.child("detalhes_viagem_motorista").child(uid).addValueEventListener(listener)
    }

    override fun buscarViagensPorOrigem(origem: String, action: (MutableList<Viagem>) -> Unit) {
        val viagens: MutableList<Viagem> = mutableListOf()

        val listener = object: ChildEventListener{
            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot?) {}
            override fun onCancelled(p0: DatabaseError?) {Log.d("DATASSS", "Error")}
            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val viagem: Viagem? = p0!!.getValue(Viagem::class.java)
                    Log.i("logBusca", viagem!!.origem)
                    viagens.add(viagem)
                    action(viagens)
            }
        }
        root.child("busca_viagens").orderByChild("origem").equalTo(origem).addChildEventListener(listener)
    }

    override fun buscarViagensPorDestino(destino: String, action: (MutableList<Viagem>) -> Unit) {
        val viagens: MutableList<Viagem> = mutableListOf()

        val listener = object: ChildEventListener{
            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot?) {}
            override fun onCancelled(p0: DatabaseError?) {Log.d("DATASSS", "Error")}
            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val viagem: Viagem? = p0!!.getValue(Viagem::class.java)
                Log.i("logBusca", viagem!!.destino)
                viagens.add(viagem)
                action(viagens)
            }
        }
        root.child("busca_viagens").orderByChild("destino").equalTo(destino).addChildEventListener(listener)
    }

    override fun buscarViagensPorOrigemDestino(origem: String, destino: String, action: (MutableList<Viagem>) -> Unit) {
        val viagens: MutableList<Viagem> = mutableListOf()

        val listener = object: ChildEventListener{
            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}
            override fun onChildRemoved(p0: DataSnapshot?) {}
            override fun onCancelled(p0: DatabaseError?) {Log.d("DATASSS", "Error")}
            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                val viagem: Viagem? = p0!!.getValue(Viagem::class.java)
                Log.i("logBusca", viagem!!.origem + viagem!!.destino)
                viagens.add(viagem)
                action(viagens)
            }
        }
        root.child("busca_viagens").orderByChild("origemdestino").equalTo(origem+destino).addChildEventListener(listener)
    }

    override fun buscarPassageiros(uid_via: String, callback: (MutableList<Passageiro>) -> Unit) {
        val passageiros: MutableList<Passageiro> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.d("DATASSS", "Error")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach {
                    val passageiro: Passageiro? = it.getValue(Passageiro::class.java)
                    if (passageiro != null) passageiros.add(passageiro)
                }
                callback(passageiros)
            }
        }

        root.child("viagens").child(uid_via).child("passageiros").addValueEventListener(listener)
    }

    override fun buscarMotorista(uid: String, sucesso: (motorista: Motorista) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val motorista: Motorista? = dataSnapshot?.getValue(Motorista::class.java)
                if (motorista != null)
                    sucesso(motorista)
            }

        }

        root.child("users").child("motoristas").child(uid).addValueEventListener(listener)
    }

    override fun buscarCarro(uid_mot: String, sucesso: (carro: Carro) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val carro: Carro? = dataSnapshot?.getValue(Carro::class.java)
                if (carro != null)
                    sucesso(carro)
            }

        }

        root.child("users").child("motoristas").child(uid_mot).child("carro").addValueEventListener(listener)
    }

    override fun reservar(uid_via: String, vagas: Int, passageiro: Passageiro, sucesso: () -> Unit) {
        root.child("viagens").child(uid_via).child("qtd_vagas").setValue(vagas)
        root.child("viagens").child(uid_via).child("passageiros").child(passageiro.uid).setValue(passageiro)
    }

    override fun removerViagemMotorista(uid_mot: String, uid_via: String, sucesso: () -> Unit) {
        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {}

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                dataSnapshot?.children?.forEach {
                    val pas: Passageiro? = it.getValue(Passageiro::class.java)
                    Log.i("logBusca","Passageiro: "+pas!!.uid)
                    root.child("viagens_passageiro_principal").child(pas.uid).child(uid_via).removeValue()

                }
                sucesso()
            }
        }
        root.child("viagens").child(uid_via).child("passageiros").addValueEventListener(listener)

        root.child("viagens").child(uid_via).removeValue()
        root.child("viagem_motorista").child(uid_mot).child(uid_via).removeValue()
        root.child("detalhes_viagens_motorista").child(uid_via).removeValue()
        root.child("detalhes_viagens_busca").child(uid_via).removeValue()
        root.child("busca_viagens").child(uid_via).removeValue()
    }


}
