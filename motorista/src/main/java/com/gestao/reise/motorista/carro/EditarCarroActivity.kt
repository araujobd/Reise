package com.gestao.reise.motorista.carro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.gestao.reise.motorista.R
import kotlinx.android.synthetic.main.activity_editar_carro.*

class EditarCarroActivity : AppCompatActivity(), EditarCarroContrato.View {

    private val presenter: EditarCarroContrato.Presenter by lazy { EditarCarroPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_carro)

        configurarBotoes()
    }

    private fun configurarBotoes() {
        bt_cadastrar.setOnClickListener { cadastrarCarro() }
        bt_cadastrar.background = getDrawable(R.color.colorAccent)
    }

    override fun done() {
        finish()
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
}
