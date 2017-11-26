package com.gestao.reise.motorista.detalhesViagem

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.detalhesPassageiros.DetalhesPassageirosActivity
import com.gestao.reise.reisecommon.model.Viagem
import kotlinx.android.synthetic.main.activity_detalhes_viagem.*

/**
 * Created by cainan on 30/10/17.
 */
class DetalhesActivity: Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_viagem)
        setActionBar(toolbarDetalhes)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        val viagem = getIntent().getSerializableExtra("detalhes") as Viagem
        actionBar.title = viagem.origem.capitalize()+" / "+viagem.destino.capitalize()
        mostrarDetalhes(viagem)
    }

    fun mostrarDetalhes(viagem: Viagem){
        tv_det_horario.text = viagem.horario
        tv_det_preco.text = viagem.preco
        tv_det_vagas.text = viagem.qtd_vagas.toString()
        tv_det_data.text = viagem.data
        //mostrarFrequencia(viagem)
    }

    /*fun mostrarPassageiros(uid_freq: String, dia: String) {
        val freq: Array<String> = arrayOf(uid_freq,dia)
        val intent = Intent(this@DetalhesActivity, DetalhesPassageirosActivity::class.java)
        intent.putExtra("freq",freq)
        startActivity(intent)
    }

    fun mostrarFrequencia(viagem: Viagem) {
        if(viagem.frequencia[0]) {
            bt_dom.setBackgroundColor(Color.MAGENTA)
            bt_dom.setOnClickListener{  mostrarPassageiros(viagem.uid_frequencia,"dom") }
        }
        if(viagem.frequencia[1]) {
            bt_seg.setBackgroundColor(Color.MAGENTA)
            bt_seg.setOnClickListener{  mostrarPassageiros(viagem.uid_frequencia,"seg")  }
        }
        if(viagem.frequencia[2]) {
            bt_ter.setBackgroundColor(Color.MAGENTA)
            bt_ter.setOnClickListener{  mostrarPassageiros(viagem.uid_frequencia,"ter")  }
        }
        if(viagem.frequencia[3]) {
            bt_qua.setBackgroundColor(Color.MAGENTA)
            bt_qua.setOnClickListener{  mostrarPassageiros(viagem.uid_frequencia,"qua")  }
        }
        if(viagem.frequencia[4]) {
            bt_qui.setBackgroundColor(Color.MAGENTA)
            bt_qui.setOnClickListener{  mostrarPassageiros(viagem.uid_frequencia,"qui")  }
        }
        if(viagem.frequencia[5]) {
            bt_sex.setBackgroundColor(Color.MAGENTA)
            bt_sex.setOnClickListener{ mostrarPassageiros(viagem.uid_frequencia,"sex")   }
        }
        if(viagem.frequencia[6]) {
            bt_sab.setBackgroundColor(Color.MAGENTA)
            bt_sab.setOnClickListener{  mostrarPassageiros(viagem.uid_frequencia,"sab")  }
        }
    }*/
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> { finish() }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}