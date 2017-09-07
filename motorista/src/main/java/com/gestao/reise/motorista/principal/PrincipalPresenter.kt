package com.gestao.reise.motorista.principal

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by cainan on 07/09/17.
 */
class PrincipalPresenter : FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage?) {
        Log.i("logNotification",p0!!.notification.body)
    }
}