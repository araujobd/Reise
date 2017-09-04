package com.gestao.reise.motorista.splash

/**
 * Created by bruno on 03/09/17.
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