package com.litil.currencyconverter.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.litil.currencyconverter.R
import com.litil.currencyconverter.data.schemas.CurrencyDatabase
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.presentation.adapters.CurrencyAdapter
import com.litil.currencyconverter.presentation.di.CurrenciesListPresenterFactory
import com.litil.currencyconverter.presentation.views.CurrenciesListView

class CurrenciesListActivity : AppCompatActivity(), CurrenciesListView {
    private lateinit var currenciesList: RecyclerView

    private val presenter by lazy {
        CurrenciesListPresenterFactory.create(this)
    }

    private val adapter = CurrencyAdapter {
//        this.presenter.
    }

//    private val db by lazy {
//        CurrencyDatabase.getDatabase(this).getCurrencyDao()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

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
}