package com.gestao.reise.motorista.principal

import android.util.Log
import com.gestao.reise.reisecommon.model.Viagem
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

    override fun onMessageReceived(p0: RemoteMessage?) {
        Log.i("logNotification",p0!!.notification.body)
    }

    override fun possuiViagens() {
        source.buscarViagens("motoristas",auth.currentUser!!.uid) { viagens ->
                if(viagens.isEmpty()){
                    Log.i("logBusca","direcionarCadastro")
                    view.direcionarCadastro()
                }else{
                    view.mostrarViagens(viagens)
                }
            }
    }

}