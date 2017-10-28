package com.gestao.reise.passageiro.principal

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.gestao.reise.passageiro.BaseActivity

import com.gestao.reise.passageiro.R
import com.gestao.reise.passageiro.buscarViagem.BuscarViagemActivity
import com.gestao.reise.passageiro.perfil.PerfilActivity
import com.gestao.reise.reisecommon.model.Passageiro
import com.gestao.reise.reisecommon.model.Viagem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.app_bar_principal.*

class PrincipalActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,PrincipalContrato.View {

    private val presenter: PrincipalContrato.Presenter by lazy { PrincipalPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
Log.i("teste","onCreate Principal")
        showProgress()

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_drawer.setNavigationItemSelectedListener(this)
        presenter.possuiViagens()
        //presenter.configNavHeader()
    }

    override fun mostrarNavHeader(passageiro: Passageiro) {
        /*val header: View = nav_drawer.getHeaderView(0)
        tv_name.setText(passageiro.nome)
        Picasso.with(this).load(passageiro.fotoUrl).noFade().into(header.nav_img_perfil)*/
    }
    override fun direcionarBusca() {
        startActivity(Intent(this@PrincipalActivity, BuscarViagemActivity::class.java))
        finish()
    }

    override fun mostrarViagens(viagens: MutableList<Viagem>) {
        Log.i("teste","mostrarViagens")
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
        if(id == R.id.nav_perfil)
            startActivity(Intent(this@PrincipalActivity, PerfilActivity::class.java))

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
