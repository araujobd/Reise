package com.gestao.reise.reisecommon

import com.gestao.reise.reisecommon.model.Viagem

/**
 * Created by cainan on 09/09/17.
 */
interface Listener {

    interface Viagens {
        fun pronto(viagens: (MutableList<Viagem>))
    }

}