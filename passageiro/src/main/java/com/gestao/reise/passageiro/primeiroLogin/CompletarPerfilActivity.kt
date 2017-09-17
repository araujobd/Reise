package com.gestao.reise.passageiro.primeiroLogin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import com.gestao.reise.passageiro.BaseActivity

import com.gestao.reise.passageiro.R
import com.gestao.reise.reisecommon.model.Passageiro
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_completar_perfil.*

class CompletarPerfilActivity : BaseActivity(), CompletarPerfilContrato.View {

    private val RC_PICK_IMAGE = 1
    private var imagePath: Uri? = null

    private val presenter: CompletarPerfilContrato.Presenter by lazy { CompletarPerfilPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completar_perfil)

        configurarTela()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imagePath = data?.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imagePath)
            img_perfil.setImageBitmap(bitmap)
        }
    }

    private fun configurarTela() {
        presenter.carregarPerfil()

        btimg_upload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Escolha uma Imagem"), RC_PICK_IMAGE)
        }

        fab_atualizar.setOnClickListener { atualizarPerfil() }
    }

    override fun mostrarPerfil(passageiro: Passageiro) {
        Picasso.with(this).load(passageiro.fotoUrl).noFade().into(img_perfil)

        ed_nome.setText(passageiro.nome)
        ed_celular.setText(passageiro.telefone)
        ed_descricao.setText(passageiro.descricao)
        ed_endereco.setText(passageiro.endereco)
    }

    private fun validar() : Boolean {
        var flag = true
        if (TextUtils.isEmpty(ed_nome.text)) {
            flag = false
            ed_nome.error = getString(R.string.campo_obrigatorio)
        }

        if (TextUtils.isEmpty(ed_celular.text)) {
            flag = false
            ed_celular.error = getString(R.string.campo_obrigatorio)
        }

        if (TextUtils.isEmpty(ed_endereco.text)) {
            flag = false
            ed_endereco.error = getString(R.string.campo_obrigatorio)
        }

        return flag
    }

    private fun atualizarPerfil() {
        if (validar())
            presenter.atualizarPerfil(imagePath,
                    ed_nome.text.toString(),
                    ed_celular.text.toString(),
                    ed_endereco.text.toString(),
                    ed_descricao.text.toString())
    }
}
