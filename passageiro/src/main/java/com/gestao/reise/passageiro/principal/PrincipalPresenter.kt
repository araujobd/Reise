package com.gestao.reise.passageiro.principal

import android.util.Log
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by cainan on 07/09/17.
 */
class PrincipalPresenter(val view: PrincipalContrato.View) : FirebaseMessagingService(), PrincipalContrato.Presenter {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun possuiViagens() {
        source.buscarViagens("passageiros", auth.currentUser!!.uid) { viagens ->
            if(viagens.isEmpty()){
                view.direcionarBusca()
            }else{
                view.mostrarViagens(viagens)
            }
        }
    }

    override fun configNavHeader() {
/*        source.buscarPassageiro(auth.currentUser!!.uid.toString()) {
            passageiro ->  view.mostrarNavHeader(passageiro)
        }*/
        val uid  = auth.currentUser!!.uid
        source.navBusca(uid, "passageiro") { nome: String, fotoUrl: String ->
            view.mostrarNavHeader(nome, fotoUrl)
        }

    }
    override fun onMessageReceived(p0: RemoteMessage?) {
        Log.i("logNotification",p0!!.notification.body)
    }
}