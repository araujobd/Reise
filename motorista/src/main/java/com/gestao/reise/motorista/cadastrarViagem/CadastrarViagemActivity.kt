package com.gestao.reise.motorista.cadastrarViagem

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.gestao.reise.motorista.R
import kotlinx.android.synthetic.main.content_cadastrar_viagem.*
import kotlin.collections.ArrayList


/**
 * Created by cainan on 20/09/17.
 */
class CadastrarViagemActivity : Activity(), CadastrarViagemContrato.view{

    private lateinit var presenter: CadastrarViagemContrato.presenter
    private val frequencia: ArrayList<Boolean> = arrayListOf(false,false,false,false,false,false,false)

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
        marcaFrequencia()
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
                    frequencia)
            finish()
        })
        builder.setNegativeButton("Não", { dialog, which ->
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }
    fun marcaFrequencia(){
        if(cb_domingo.isChecked)
            frequencia[0] = true
        if(cb_segunda.isChecked)
            frequencia[1] = true
        if(cb_terca.isChecked)
            frequencia[2] = true
        if(cb_quarta.isChecked)
            frequencia[3] = true
        if(cb_quinta.isChecked)
            frequencia[4] = true
        if(cb_sexta.isChecked)
            frequencia[5] = true
        if(cb_sabado.isChecked)
            frequencia[6] = true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> { finish() }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

}