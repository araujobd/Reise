package com.gestao.reise.motorista.cadastrarViagem

import android.app.Activity
import android.os.Bundle
import com.gestao.reise.motorista.R
import kotlinx.android.synthetic.main.content_cadastrar_viagem.*
import java.util.*
import java.text.Normalizer
import java.util.Collections.replaceAll



/**
 * Created by cainan on 20/09/17.
 */
class CadastrarViagemActivity : Activity(), CadastrarViagemContrato.view{

    private lateinit var presenter: CadastrarViagemContrato.presenter
    private val frequencia: HashMap<String, Boolean> = hashMapOf("domingo" to false, "segunda" to false, "terca" to false
            , "quarta" to false, "quinta" to false, "sexta" to false, "sabado" to false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_cadastrar_viagem)
        presenter = CadastrarViagemPresenter(this)
        bt_salvar.setOnClickListener{
            salvarViagem()
            finish()
        }
    }
    override fun msgSucesso() {

    }

    fun removerAcentos(str: String): String = Normalizer.normalize(str, Normalizer.Form.NFD)

    fun salvarViagem(){
        marcaFrequencia()
        presenter.cadastrarViagem(
                et_origem.text.toString().toLowerCase(),
                et_destino.text.toString().toLowerCase(),
                et_preco.text.toString(),
                et_horario.text.toString(),
                frequencia)
    }

    fun marcaFrequencia(){
        if(cb_domingo.isChecked)
            frequencia.set("domingo",true)
        if(cb_segunda.isChecked)
            frequencia.set("segunda",true)
        if(cb_terca.isChecked)
            frequencia.set("terca",true)
        if(cb_quarta.isChecked)
            frequencia.set("quarta",true)
        if(cb_quinta.isChecked)
            frequencia.set("quinta",true)
        if(cb_sexta.isChecked)
            frequencia.set("sexta",true)
        if(cb_sabado.isChecked)
            frequencia.set("sabado",true)
    }

}