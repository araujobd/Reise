package com.gestao.reise.motorista.perfil.editar

import android.content.Intent
import com.gestao.reise.motorista.principal.PrincipalActivity
import com.gestao.reise.reisecommon.perfil.editar.BaseEditarPerfilActivity
import com.gestao.reise.reisecommon.perfil.editar.EditarPerfilContrato

/**
 * Created by bruno on 05/10/17.
 */
class EditarPerfilActivity : BaseEditarPerfilActivity() {

    override val presenter: EditarPerfilContrato.Presenter by lazy { EditarPerfilPresenter(this) }

    override fun iniciarPrincipal() {
        startActivity(Intent(this@EditarPerfilActivity, PrincipalActivity::class.java))
        finish()
    }
}