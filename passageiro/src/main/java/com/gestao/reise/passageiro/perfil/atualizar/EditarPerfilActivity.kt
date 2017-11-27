package com.gestao.reise.passageiro.perfil.atualizar

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import com.gestao.reise.passageiro.BaseActivity
import com.gestao.reise.passageiro.R
import com.gestao.reise.passageiro.principal.PrincipalActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_completar_perfil.*
import kotlinx.android.synthetic.main.content_completar_perfil.*

/**
 * Created by bruno on 26/11/17.
 */

class EditarPerfilActivity : BaseActivity(), EditarPerfilContrato.View {

    private val RC_PICK_IMAGE = 1
    private var imagePath: Uri? = null
    private val presenter: EditarPerfilContrato.Presenter by lazy { EditarPerfilPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completar_perfil)

        configurarTela()
        inicializar()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null)
             imagePath = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imagePath)
            img_perfil.setImageBitmap(bitmap)
        }
    }

    protected fun configurarTela() {
        setActionBar(toolbar)
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.title = "Atualizar Perfil"

        presenter.carregarPerfil()
    }

    protected fun inicializar() {
        btimg_upload.setOnClickListener { escolherImagem() }
        fab_atualizar.setOnClickListener { atualizarPerfil() }
    }

    protected fun escolherImagem() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(Intent.createChooser(intent, "Escolha uma imagem"), RC_PICK_IMAGE)
    }

    override fun mostrarPerfil(fotoUrl: String, nome: String, telefone: String, endereco: String, descricao: String) {
        if (fotoUrl.length > 0)
            Picasso.with(this)
                    .load(fotoUrl)
                    .noFade()
                    .into(img_perfil)

        ed_nome.setText(nome)
        ed_celular.setText(telefone)
        ed_endereco.setText(endereco)
        ed_descricao.setText(descricao)
    }

    protected fun atualizarPerfil() {
        Log.d("Image" , imagePath.toString())
        if (validar()) {
            presenter.atualizarPerfil(imagePath,
                    ed_nome.text.toString(),
                    ed_celular.text.toString(),
                    ed_endereco.text.toString(),
                    ed_descricao.text.toString())
        }
    }

    private fun validar(): Boolean {
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

    override fun ePrimeiroLogin(): Boolean {
        val s = intent.getStringExtra("primeiro")
        if (s != null)
            return true
        return false
    }

    override fun iniciarPrincipal() {
        startActivity(Intent(this@EditarPerfilActivity, PrincipalActivity::class.java))
        finish()
    }
}