package com.gestao.reise.motorista.detalhesPassageiros

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.principal.Adaptador
import com.gestao.reise.reisecommon.model.Passageiro
import kotlinx.android.synthetic.main.activity_detalhes_passageiros.*

/**
 * Created by cainan on 30/10/17.
 */
class DetalhesPassageirosActivity: Activity(), DetalhesPassageirosContrato.view {

    private val presenter: DetalhesPassageirosContrato.presenter by lazy { DetalhesPassageirosPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_passageiros)
        val freq = intent.getStringArrayExtra("freq")
        setActionBar(toolbarDetalhesPas)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.title = freq[1]+"/ Passageiros"
        presenter.buscarFrequencia(freq)
    }

    override fun mostrarPassageiros(passageiros: MutableList<Passageiro>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPas)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = Adaptador(passageiros)
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