package com.gestao.reise.motorista.principal

import android.content.Intent
import android.util.Log
import com.gestao.reise.reisecommon.Listener
import com.gestao.reise.reisecommon.model.Viagem
import com.gestao.reise.reisecommon.source.DataSource
import com.gestao.reise.reisecommon.source.DataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by cainan on 07/09/17.
 */
class PrincipalPresenter(val view: PrincipalContrato.View) : FirebaseMessagingService(), PrincipalContrato.Presenter, Listener {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val source: DataSource = DataSourceImpl

    override fun onMessageReceived(p0: RemoteMessage?) {
        Log.i("logNotification",p0!!.notification.body)
    }

    override fun possuiViagens() {
        source.buscarViagens("motorista",auth.currentUser!!.uid, object : Listener.Viagens{
            override fun prontoPassageiro(viagens: MutableList<Viagem>) {/*Tem nada*/}
            override fun prontoMotorista(viagens: (MutableList<Viagem>)) {
                if(viagens.isEmpty()){
                    view.direcionarCadastro()
                }else{
                    view.mostrarViagens(viagens)
                }
            }

        })
    }

}