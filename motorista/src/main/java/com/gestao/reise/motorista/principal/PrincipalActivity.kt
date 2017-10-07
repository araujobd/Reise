package com.gestao.reise.motorista.principal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.cadastrarViagem.CadastrarViagemActivity
import com.gestao.reise.reisecommon.model.Viagem
import kotlinx.android.synthetic.main.activity_principal.*

/**
 * Created by cainan on 07/09/17.
 */
class PrincipalActivity : Activity(), PrincipalContrato.View{
    private val presenter: PrincipalContrato.Presenter by lazy { PrincipalPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        presenter.possuiViagens()
    }

    override fun direcionarCadastro() {
        startActivity(Intent(this@PrincipalActivity, CadastrarViagemActivity::class.java))
        finish()
    }

    override fun mostrarViagens(viagens: MutableList<Viagem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMot)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val adapter = Adaptador(viagens)
        recyclerView.adapter = adapter
    }
}