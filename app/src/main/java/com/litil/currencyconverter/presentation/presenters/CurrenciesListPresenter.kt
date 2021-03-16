package com.litil.currencyconverter.presentation.presenters

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.litil.currencyconverter.data.converters.fromDatabaseCurrency
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.usecases.CreateDatabaseCurrencyUseCase
import com.litil.currencyconverter.domain.usecases.GetDatabaseCurrenciesUseCase
import com.litil.currencyconverter.domain.usecases.GetHttpCurrenciesUseCase
import com.litil.currencyconverter.presentation.views.CurrenciesListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrenciesListPresenter(
    private val getHttpCurrenciesUseCase: GetHttpCurrenciesUseCase,
    private val createDatabaseCurrencyUseCase: CreateDatabaseCurrencyUseCase,
    private val getDatabaseCurrenciesUseCase: GetDatabaseCurrenciesUseCase,
    private val context: Context
): BasePresenter<CurrenciesListView>() {
    fun onViewResumed() {
        this.getHttpCurrenciesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.valute?.values?.let { index ->
                    index.forEach { i ->
                        createDatabaseCurrencyUseCase(
                            charCode = i.charCode,
                            name = i.name,
                            value = i.value,
                            prevValue = i.prevValue
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({}, {})
                        .untilDestroyed()
                    }
                }
            }, {})
            .untilDestroyed()

        this.getDatabaseCurrenciesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val list = emptyList<Currency>().toMutableList()
                for (i in it) {
                    list += i.fromDatabaseCurrency()
                }
                this.view?.bindCurrenciesList(list)
            }, {
                Log.e("ERROR1488", it.toString())
            })
            .untilDestroyed()
    }
}