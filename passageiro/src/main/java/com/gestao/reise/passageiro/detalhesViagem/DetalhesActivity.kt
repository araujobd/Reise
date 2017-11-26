package com.gestao.reise.passageiro.detalhesViagem

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
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
        setActionBar(toolbarDetalhes)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        val viagem = getIntent().getSerializableExtra("detalhes") as Viagem
        actionBar.setTitle(viagem.origem.capitalize()+" / "+viagem.destino.capitalize())
        mostrarDetalhes(viagem)
    }

    fun mostrarDetalhes(viagem: Viagem) {
        //tv_det_origem.setText(viagem.origem)
        //tv_det_destino.setText(viagem.destino)
        tv_det_horario.setText(viagem.horario)
        tv_det_preco.setText(viagem.preco)
        tv_det_vagas.text = viagem.qtd_vagas.toString()
        tv_det_data.text = viagem.data
        bt_reservar.setOnClickListener{ presenter.interesseVaga(viagem) }
        //mostrarFrequencia(viagem)
    }


    override fun dialogo(viagem: Viagem) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Deseja reservar vaga neste dia?")
        builder.setPositiveButton("Sim", { dialog, which ->
            presenter.reservarVaga(viagem)
            finish()
        })
        builder.setNegativeButton("Não", { dialog, which ->
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }

    fun mostrarFrequencia(viagem: Viagem){

        /*if(viagem.frequencia[0]) {
            bt_dom.background = getDrawable(R.drawable.circle_tint)
            bt_dom.setOnClickListener{ presenter.interesseVaga(viagem,"dom") }
        }
        if(viagem.frequencia[1]) {
            bt_seg.background = getDrawable(R.drawable.circle_tint)
            bt_seg.setOnClickListener{ presenter.interesseVaga(viagem,"seg") }
        }
        if(viagem.frequencia[2]) {
            bt_ter.background = getDrawable(R.drawable.circle_tint)
            bt_ter.setOnClickListener{ presenter.interesseVaga(viagem,"ter") }
        }
        if(viagem.frequencia[3]) {
            bt_qua.background = getDrawable(R.drawable.circle_tint)
            bt_qua.setOnClickListener{ presenter.interesseVaga(viagem,"qua") }
        }
        if(viagem.frequencia[4]) {
            bt_qui.background = getDrawable(R.drawable.circle_tint)
            bt_qui.setOnClickListener{ presenter.interesseVaga(viagem,"qui") }
        }
        if(viagem.frequencia[5]) {
            bt_sex.background = getDrawable(R.drawable.circle_tint)
            bt_sex.setOnClickListener{ presenter.interesseVaga(viagem,"sex") }
        }
        if(viagem.frequencia[6]) {
            bt_sab.background = getDrawable(R.drawable.circle_tint)
            bt_sab.setOnClickListener{ presenter.interesseVaga(viagem,"sab") }
         }*/
    }

    override fun msgSucesso() {
        Toast.makeText(this,"Viagem reservada com sucesso!!",Toast.LENGTH_LONG).show()
    }
    override fun msgErro() {
        Toast.makeText(this,"A viagem está cheia.",Toast.LENGTH_LONG).show()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> { finish() }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}