package com.gestao.reise.passageiro.perfil.editar

import android.net.Uri
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.perfil.editar.EditarPerfilContrato
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by bruno on 05/10/17.
 */
class EditarPerfilPresenter(val view: EditarPerfilContrato.View) : EditarPerfilContrato.Presenter {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val source: DataSource by lazy { DataSourceImpl }

    override fun carregarPerfil() {
        source.buscarPerfilPassageiro(auth.currentUser?.uid!!) {
            view.mostrarPerfil(it.fotoUrl, it.nome, it.telefone, it.endereco, it.descricao)
        }
    }

    override fun atualizarPerfil(imagePath: Uri?, nome: String, telefone: String, endereco: String, descricao: String) {
        val uid: String = auth.currentUser?.uid!!
        val passageiro = Passageiro()

        passageiro.uid = uid
        passageiro.nome = nome
        passageiro.telefone = telefone
        passageiro.endereco = endereco
        passageiro.descricao = descricao

        if (imagePath != null)
            source.salvarImagem("passageiro", uid, imagePath) {
                passageiro.fotoUrl = it
                source.salvarPassageiro(passageiro)
            }
        else
            source.salvarPassageiro(passageiro)

        escolherAcao()
    }

    override fun escolherAcao() {
        if (view.ePrimeiroLogin())
            view.iniciarPrincipal()
        else
            view.finish()
    }

}