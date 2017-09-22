package com.gestao.reise.passageiro.splash

import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.splash.BaseSplashPresenter
import com.gestao.reise.reisecommon.splash.SplashContrato
import com.google.firebase.auth.FirebaseUser

/**
 * Created by bruno on 02/09/17.
 */
class SplashPresenter(view: SplashContrato.View) : BaseSplashPresenter(view) {

    override val typeUser: String = "passageiros"

    override fun salvarUsuario(user: FirebaseUser) {
        val passageiro = Passageiro()

        passageiro.uid = user.uid
        passageiro.nome = user.displayName.toString()
        passageiro.fotoUrl = user.photoUrl.toString()

        source.salvarPassageiro(passageiro)
    }
}