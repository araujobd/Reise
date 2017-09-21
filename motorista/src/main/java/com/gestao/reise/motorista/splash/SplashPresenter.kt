package com.gestao.reise.motorista.splash

import com.gestao.reise.reisecommon.model.Motorista
import com.gestao.reise.reisecommon.splash.BaseSplashPresenter
import com.gestao.reise.reisecommon.splash.SplashContrato
import com.google.firebase.auth.FirebaseUser

/**
 * Created by bruno on 03/09/17.
 */

class SplashPresenter(view: SplashContrato.View) : BaseSplashPresenter(view) {

    override val typeUser: String = "passageiros"


    override fun salvarUsuario(user: FirebaseUser) {
        val motorista = Motorista()

        motorista.uid = user.uid
        motorista.nome = user.displayName.toString()
        motorista.fotoUrl = user.photoUrl.toString()

        source.salvarMotorista(motorista)
    }

}