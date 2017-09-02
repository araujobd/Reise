package com.gestao.reise.reisecommon.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

import com.gestao.reise.reisecommon.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContrato.View, GoogleApiClient.OnConnectionFailedListener {

    private val RC_SIGN_IN = 9001

    private lateinit var presenter: LoginContrato.Presenter
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        configureScreen()
        configureGoogleSignin()
        configureFacebookSignin()
    }

    private fun configureScreen() {
        presenter = LoginPresenter(this)

        signin_google.setOnClickListener { signinWithGoogle() }
    }

    private fun configureGoogleSignin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    private fun configureFacebookSignin() {
        callbackManager = CallbackManager.Factory.create()
        signin_facebook.setReadPermissions("email", "public_profile")
        signin_facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onError(error: FacebookException?) {

            }

            override fun onSuccess(result: LoginResult?) {
                presenter.signinWithFacebook(result?.accessToken)
            }

            override fun onCancel() {
            }

        })
    }
    override fun iniciarPrincipal() {
    }

    override fun ExibirErro() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            RC_SIGN_IN -> {
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                if (result.isSuccess) presenter.signinWithGoogle(result.signInAccount)
            }
            else -> {
                callbackManager.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this, "ERROR " + p0, Toast.LENGTH_SHORT).show()
    }


    private fun signinWithGoogle() {
        val googleIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(googleIntent, RC_SIGN_IN)
    }
}
