package com.gestao.reise.motorista.principal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import com.gestao.reise.motorista.R
import com.gestao.reise.motorista.cadastrarViagem.CadastrarViagemActivity
import com.gestao.reise.reisecommon.model.Viagem
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.app_bar_principal.*

/**
 * Created by cainan on 07/09/17.
 */
class PrincipalActivity : Activity(), NavigationView.OnNavigationItemSelectedListener, PrincipalContrato.View{

    private val presenter: PrincipalContrato.Presenter by lazy { PrincipalPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_drawer.setNavigationItemSelectedListener(this)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId

        if(id == R.id.nav_viagem)
            startActivity(Intent(this@PrincipalActivity, CadastrarViagemActivity::class.java))
        if(id == R.id.nav_perfil)
           // startActivity(Intent(this@PrincipalActivity, PerfilActivity::class.java))

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}