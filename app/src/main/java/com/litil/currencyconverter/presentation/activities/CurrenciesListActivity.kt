package com.litil.currencyconverter.presentation.activities

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.view.ViewConfiguration
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.litil.currencyconverter.R
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.presentation.adapters.CurrencyAdapter
import com.litil.currencyconverter.presentation.di.CurrenciesListPresenterFactory
import com.litil.currencyconverter.presentation.views.CurrenciesListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.concurrent.thread

class CurrenciesListActivity : AppCompatActivity(), CurrenciesListView {
    private lateinit var currenciesList: RecyclerView

    private val presenter by lazy {
        CurrenciesListPresenterFactory.create(this)
    }

    private val adapter = CurrencyAdapter {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        this.presenter.attachView(this)
        this.currenciesList = findViewById(R.id.currencies_list)

        this.currenciesList.adapter = this.adapter
    }

    override fun onResume() {
        super.onResume()
        this.presenter.onViewResumed()
    }

    override fun bindCurrenciesList(list: List<Currency>) {
        this.adapter.currenciesList = list
    }

    override fun onDestroy() {
        this.presenter.detachView()
        super.onDestroy()
    }

    private fun hasNavigationBar(): Boolean {
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        return id > 0 && resources.getBoolean(id)
    }
}