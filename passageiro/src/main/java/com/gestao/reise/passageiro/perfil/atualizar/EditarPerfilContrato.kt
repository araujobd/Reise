package com.gestao.reise.passageiro.perfil.atualizar

import android.net.Uri

/**
 * Created by bruno on 26/11/17.
 */
interface EditarPerfilContrato {
    interface View {
        fun iniciarPrincipal()
        fun mostrarPerfil(fotoUrl: String, nome: String, telefone: String, endereco: String, descricao: String)
        fun ePrimeiroLogin(): Boolean
        fun finish()
    }

    interface Presenter {
        fun carregarPerfil()
        fun atualizarPerfil(imagePath: Uri?, nome: String, telefone: String, endereco: String, descricao: String)
        fun escolherAcao()
    }
}