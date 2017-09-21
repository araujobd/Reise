package com.gestao.reise.reisecommon.splash

/**
 * Created by bruno on 21/09/17.
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
