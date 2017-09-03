package com.gestao.reise.passageiro.splash

import com.google.firebase.auth.AuthCredential

/**
 * Created by arthur on 30/08/17.
 */

interface SplashContrato {

    interface View {
        fun logar()
        fun iniciarPrincipal()
        fun primeiroLogin()
    }

    interface Presenter {
        fun escolherAcao()
        fun verificarLogin()
    }

}