package com.gestao.reise.motorista.carro

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.cadastrarViagem.CadastrarViagemActivity
import com.gestao.reise.reisecommon.model.Carro
import kotlinx.android.synthetic.main.activity_editar_carro.*

class EditarCarroActivity : AppCompatActivity(), EditarCarroContrato.View {

    private val presenter: EditarCarroContrato.Presenter by lazy { EditarCarroPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_carro)
        setActionBar(toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeButtonEnabled(true)
        actionBar.title = "Cadastrar Carro"
        presenter.carregarCarro()
        configurarBotoes()
    }

    private fun configurarBotoes() {
        bt_cadastrar.setOnClickListener { cadastrarCarro() }
    }

    override fun done() {
        startActivity(Intent(this@EditarCarroActivity, CadastrarViagemActivity::class.java))
        finish()
    }

    override fun entregarCarro(carro: Carro) {
        ed_cor.setText(carro.cor)
        ed_modelo.setText(carro.modelo)
        ed_placa.setText(carro.placa)
        ed_qtd_vagas.setText(carro.qtd_vagas.toString())
    }

    private fun cadastrarCarro() {
        if (validar()) {
            presenter.cadastrarCarro(
                    ed_cor.text.toString(),
                    ed_modelo.text.toString(),
                    ed_placa.text.toString(),
                    ed_qtd_vagas.text.toString().toInt(),
                    sw_vaga_crianca.isChecked
            )
        }
    }

    private fun validar(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(ed_modelo.text)) {
            flag = false
            ed_modelo.error = getString(R.string.campo_obrigatorio)
        }

        if (TextUtils.isEmpty(ed_cor.text)) {
            flag = false
            ed_cor.error = getString(R.string.campo_obrigatorio)
        }

        if (TextUtils.isEmpty(ed_placa.text)) {
            flag = false
            ed_placa.error = getString(R.string.campo_obrigatorio)
        }

        if (TextUtils.isEmpty(ed_qtd_vagas.text)) {
            flag = false
            ed_qtd_vagas.error = getString(R.string.campo_obrigatorio)
        }

        return flag
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                startActivity(Intent(this@EditarCarroActivity, CadastrarViagemActivity::class.java))
                finish()
            }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}
