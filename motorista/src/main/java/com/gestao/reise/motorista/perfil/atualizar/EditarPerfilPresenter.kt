package com.gestao.reise.motorista.perfil.atualizar

import android.net.Uri
import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth

class EditarPerfilPresenter(val view: EditarPerfilContrato.View) : EditarPerfilContrato.Presenter {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val source: DataSource by lazy { DataSourceImpl }

    override fun carregarPerfil() {
        source.buscarMotorista(auth.currentUser?.uid!!) {
            view.mostrarPerfil(it.fotoUrl, it.nome, it.telefone, it.endereco, it.descricao)
        }
    }

    override fun atualizarPerfil(imagePath: Uri?, nome: String, telefone: String, endereco: String, descricao: String) {
        val uid: String = auth.currentUser?.uid!!
        val motorista = Motorista()

        motorista.uid = uid
        motorista.nome = nome
        motorista.telefone = telefone
        motorista.endereco = endereco
        motorista.descricao = descricao

        if (imagePath != null)
            source.salvarImagem(uid, imagePath) {
                motorista.fotoUrl = it
                source.salvarMotorista(motorista)
            }
        else
            source.salvarMotorista(motorista)

        escolherAcao()
    }

    override fun escolherAcao() {
        if (view.ePrimeiroLogin())
            view.iniciarPrincipal()
        else
            view.finish()
    }
}
