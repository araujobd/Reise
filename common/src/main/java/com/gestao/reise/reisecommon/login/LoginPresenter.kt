package com.gestao.reise.reisecommon.login

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * Created by bruno on 01/09/17.
 */

class LoginPresenter(val view: LoginContrato.View) : LoginContrato.Presenter {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun verificarLogin() {
        if (auth.currentUser != null)
            view.returnUserLogged()
    }

    override fun signinWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        signinFirebase(credential)
    }

    override fun signinWithFacebook(token: AccessToken?) {
        val credential = FacebookAuthProvider.getCredential(token?.token!!)
        signinFirebase(credential)
    }

    private fun signinFirebase(credential: AuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                view.returnUserLogged()
            } else {
                view.exibirErro(it.exception.toString())
            }
        }
    }

}