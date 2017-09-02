package com.gestao.reise.reisecommon.login

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.*

/**
 * Created by bruno on 01/09/17.
 */

class LoginPresenter(val view: LoginContrato.View) : LoginContrato.Presenter {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun verificarLogin() {
        if (auth.currentUser != null)
            view.iniciarPrincipal()
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
                val user = auth.currentUser
                view.iniciarPrincipal()
            } else {
                view.ExibirErro()
            }
        }
    }

}