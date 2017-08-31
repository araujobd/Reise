package com.gestao.reise.passageiro.splash

/**
 * Created by arthur on 30/08/17.
 */

interface SplashContrato {

    interface View {
        fun logar()
        fun iniciarPrincipal()
    }

    interface Presenter {
        fun verificarLogin()
        fun finish()
    }

}