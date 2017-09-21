package com.gestao.reise.reisecommon.login

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

/**
 * Created by bruno on 01/09/17.
 */
interface LoginContrato {

    interface View {
        fun returnUserLogged()
        fun exibirErro(mensagem: String)
    }

    interface Presenter {
        fun verificarLogin()
        fun signinWithGoogle(result: GoogleSignInAccount?)
        fun signinWithFacebook(token: AccessToken?)
    }
}