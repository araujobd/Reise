package com.gestao.reise.passageiro.detalhesViagem

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Motorista
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
        presenter.buscaMotorista(viagem.uid_mot)
        tv_det_horario.text = viagem.horario
        tv_det_preco.text = viagem.preco
        tv_det_vagas.text = viagem.qtd_vagas.toString()
        tv_det_data.text = viagem.data
        bt_reservar.setOnClickListener{ presenter.interesseVaga(viagem) }

    }

    override fun devolveMotorista(mot: Motorista) {
        tv_nome_mot.text = mot.nome
        tv_tel_mot.text = mot.telefone
        tv_car_modelo.text = mot.carro.modelo
        tv_car_cor.text = mot.carro.cor
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