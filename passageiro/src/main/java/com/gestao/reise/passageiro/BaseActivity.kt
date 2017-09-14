package com.gestao.reise.passageiro

import android.app.Activity
import android.view.View
import android.widget.ProgressBar

/**
 * Created by bruno on 13/09/17.
 */
abstract class BaseActivity : Activity() {
    private val progressBar: ProgressBar by lazy { ProgressBar(applicationContext, null, android.R.attr.progressBarStyle) }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = ProgressBar.GONE
    }
}