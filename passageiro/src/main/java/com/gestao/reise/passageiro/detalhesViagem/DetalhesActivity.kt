package com.gestao.reise.passageiro.detalhesViagem

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Viagem
import kotlinx.android.synthetic.main.activity_detalhes_viagem.*

/**
 * Created by cainan on 15/10/17.
 */
class DetalhesActivity: Activity(), DetalhesContrato.view {

    private val presenter: DetalhesContrato.presenter by lazy { DetalhesPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_viagem)

        val viagem = getIntent().getSerializableExtra("detalhes") as Viagem
        mostrarDetalhes(viagem)
    }

    fun mostrarDetalhes(viagem: Viagem) {
        tv_det_origem.setText(viagem.origem)
        tv_det_destino.setText(viagem.destino)
        tv_det_horario.setText(viagem.horario)
        tv_det_preco.setText(viagem.preco)
        tv_det_vagas.setText(viagem.horario)
        mostrarFrequencia(viagem.frequencia)
        bt_reservar.setOnClickListener{ presenter.reservarVaga(viagem) }
    }
    fun mostrarFrequencia(frequencia: HashMap<String, Boolean>){
        if(frequencia.get("domingo")!!.equals(true))
            tv_dom.setTextColor(Color.BLUE)
        if(frequencia.get("segunda")!!.equals(true))
            tv_seg.setTextColor(Color.BLUE)
        if(frequencia.get("terca")!!.equals(true))
            tv_ter.setTextColor(Color.BLUE)
        if(frequencia.get("quarta")!!.equals(true))
            tv_qua.setTextColor(Color.BLUE)
        if(frequencia.get("quinta")!!.equals(true))
            tv_qui.setTextColor(Color.BLUE)
        if(frequencia.get("sexta")!!.equals(true))
            tv_sex.setTextColor(Color.BLUE)
        if(frequencia.get("sabado")!!.equals(true))
            tv_sab.setTextColor(Color.BLUE)
    }

    override fun msgSucesso() {
        Toast.makeText(this,"Viagem reservada com sucesso!!",Toast.LENGTH_LONG).show()
    }

}