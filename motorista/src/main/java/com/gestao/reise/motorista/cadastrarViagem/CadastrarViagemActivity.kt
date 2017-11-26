package com.gestao.reise.motorista.cadastrarViagem

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.principal.PrincipalActivity
import kotlinx.android.synthetic.main.content_cadastrar_viagem.*
import kotlin.collections.ArrayList


/**
 * Created by cainan on 20/09/17.
 */
class CadastrarViagemActivity : Activity(), CadastrarViagemContrato.view{

    private lateinit var presenter: CadastrarViagemContrato.presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_cadastrar_viagem)
        setActionBar(toolbarCadastrar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.title = "Cadastrar Viagem"
        presenter = CadastrarViagemPresenter(this)

        configBotao()
    }

    private fun configBotao() {
        bt_salvar.setOnClickListener{
            salvarViagem()
        }
    }

    override fun msgSucesso() {
        Toast.makeText(this@CadastrarViagemActivity,"Viagem cadastrada com sucesso!!",Toast.LENGTH_LONG).show()
    }

    fun salvarViagem(){
        dialogo()
    }
    fun dialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Deseja salvar a viagem?")
        builder.setPositiveButton("Sim", { dialog, which ->
            presenter.cadastrarViagem(
                    et_origem.text.toString().toLowerCase(),
                    et_destino.text.toString().toLowerCase(),
                    et_preco.text.toString(),
                    et_horario.text.toString(),
                    et_data.text.toString()
            )
            finish()
        })
        builder.setNegativeButton("Não", { dialog, which ->
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                startActivity(Intent(this@CadastrarViagemActivity, PrincipalActivity::class.java))
                finish()
            }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}