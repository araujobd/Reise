package com.gestao.reise.passageiro.primeiroLogin

import android.net.Uri
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 13/09/17.
 */
class CompletarPerfilPresenter(val view: CompletarPerfilContrato.View) : CompletarPerfilContrato.Presenter {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val source: DataSource by lazy { DataSourceImpl }

    override fun carregarPerfil() {
        source.buscarPassageiro(auth.currentUser?.uid!!) {
            passageiro ->  view.mostrarPerfil(passageiro)
        }
    }

    override fun atualizarPerfil(imagePath: Uri?, nome: String, celular: String, endereco: String, descricao: String) {
        val uid: String = auth.currentUser?.uid!!
        val passageiro = Passageiro()

        passageiro.uid = uid
        passageiro.nome = nome
        passageiro.telefone = celular
        passageiro.endereco = endereco
        passageiro.descricao = descricao

        if (imagePath != null)
            source.salvarImagem(uid, imagePath) {
                passageiro.fotoUrl = it
                source.salvarPassageiro(passageiro)
            }
        else
            source.salvarPassageiro(passageiro)

    }

}