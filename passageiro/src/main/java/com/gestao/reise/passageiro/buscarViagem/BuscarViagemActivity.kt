package com.gestao.reise.passageiro.buscarViagem

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Viagem
import kotlinx.android.synthetic.main.activity_buscar_viagem.*

/**
 * Created by cainan on 07/10/17.
 */
class BuscarViagemActivity: Activity(),BuscarViagemContrato.View {

    private lateinit var presenter: BuscarViagemContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_viagem)
        presenter = BuscarViagemPresenter(this)
        configurarBotao()
    }

    private fun configurarBotao() {
        bt_buscar.setOnClickListener{
            presenter.contemViagens(
                et_origem.text.toString(),
                et_destino.text.toString()
            )
        }
    }

    override fun mostrarMSG() {
        Toast.makeText(this,"Não possuem viagens com estes dados!",Toast.LENGTH_SHORT).show()
    }

    override fun listarViagens(viagens: MutableList<Viagem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPas)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = Adaptador(viagens)
        recyclerView.adapter = adapter
    }

}