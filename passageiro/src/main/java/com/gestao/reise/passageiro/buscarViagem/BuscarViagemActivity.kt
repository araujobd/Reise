package com.gestao.reise.passageiro.buscarViagem

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Viagem
import kotlinx.android.synthetic.main.activity_buscar_viagem.*
import android.content.Intent



/**
 * Created by cainan on 07/10/17.
 */
class BuscarViagemActivity: Activity(),BuscarViagemContrato.View {

    private lateinit var presenter: BuscarViagemContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_viagem)
        setActionBar(toolbarBuscar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.title = "Buscar Viagem"
        presenter = BuscarViagemPresenter(this)
        configurarBotao()
    }

    private fun configurarBotao() {
        bt_buscar.setOnClickListener{
            presenter.contemViagens(
                et_origem.text.toString().toLowerCase(),
                et_destino.text.toString().toLowerCase()
            )
        }
    }

    override fun mostrarMSG() {
        Toast.makeText(this,"Não possuem viagens com estes dados!",Toast.LENGTH_SHORT).show()
    }

    override fun listarViagens(viagens: MutableList<Viagem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPasBusca)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = Adaptador(viagens)
        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> { finish() }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}