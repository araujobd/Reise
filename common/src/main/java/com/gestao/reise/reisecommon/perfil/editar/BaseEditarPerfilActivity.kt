package com.gestao.reise.reisecommon.perfil.editar

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.gestao.reise.reisecommon.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_editar_perfil.*
import kotlinx.android.synthetic.main.appbar_editar_perfil.*
import kotlinx.android.synthetic.main.content_editar_perfil.*

/**
 * Created by bruno on 24/09/17.
 */
abstract class BaseEditarPerfilActivity : AppCompatActivity(), EditarPerfilContrato.View {

    protected val RC_PICK_IMAGE = 1
    protected var imagePath: Uri? = null
    abstract protected val presenter: EditarPerfilContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        configurarTela()
        inicializar()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imagePath)
            img_perfil.setImageBitmap(bitmap)
        }
    }

    private fun configurarTela() {
        setActionBar(toolbar)
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.title = "Atualizar Perfil"

        presenter.carregarPerfil()
    }

    protected fun inicializar() {
        btimg_upload.setOnClickListener { escolherImagem() }
        fab_editar.setOnClickListener { atualizarPerfil() }
    }

    private fun escolherImagem() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(Intent.createChooser(intent, "Escolha uma imagem"), RC_PICK_IMAGE)
    }

    override fun mostrarPerfil(fotoUrl: String, nome: String, telefone: String, endereco: String, descricao: String) {
        Picasso.with(this)
                .load(fotoUrl)
                .noFade()
                .into(img_perfil)

        ed_nome.setText(nome)
        ed_telefone.setText(telefone)
        ed_endereco.setText(endereco)
        ed_descricao.setText(descricao)
    }

    protected fun atualizarPerfil() {
        if (validar()) {
            presenter.atualizarPerfil(imagePath,
                    ed_nome.text.toString(),
                    ed_telefone.text.toString(),
                    ed_endereco.text.toString(),
                    ed_descricao.text.toString())
            iniciarPrincipal()
        }
    }

    protected fun validar(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(ed_nome.text)) {
            flag = false
            ed_nome.error = getString(R.string.campo_obrigatorio)
        }

        if (TextUtils.isEmpty(ed_telefone.text)) {
            flag = false
            ed_telefone.error = getString(R.string.campo_obrigatorio)
        }

        if (TextUtils.isEmpty(ed_endereco.text)) {
            flag = false
            ed_endereco.error = getString(R.string.campo_obrigatorio)
        }

        return flag
    }

    override fun ePrimeiroLogin(): Boolean {
        val s = intent.getStringExtra("primeiro")
        if (s != null)
            return true
        return false
    }

}