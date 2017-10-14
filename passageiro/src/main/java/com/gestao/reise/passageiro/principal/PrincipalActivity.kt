package com.gestao.reise.passageiro.principal

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.gestao.reise.passageiro.BaseActivity

import com.gestao.reise.passageiro.R
import com.gestao.reise.passageiro.buscarViagem.BuscarViagemActivity
import com.gestao.reise.reisecommon.model.Viagem
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.app_bar_principal.*

class PrincipalActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,PrincipalContrato.View {

    private val presenter: PrincipalContrato.Presenter by lazy { PrincipalPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        showProgress()

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_drawer.setNavigationItemSelectedListener(this)
        presenter.possuiViagens()
    }
    override fun direcionarBusca() {
        startActivity(Intent(this@PrincipalActivity, BuscarViagemActivity::class.java))
        finish()
    }

    override fun mostrarViagens(viagens: MutableList<Viagem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPas)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = Adaptador(viagens)
        recyclerView.adapter = adapter
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId

        if(id == R.id.nav_viagem)
            startActivity(Intent(this@PrincipalActivity, BuscarViagemActivity::class.java))

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
