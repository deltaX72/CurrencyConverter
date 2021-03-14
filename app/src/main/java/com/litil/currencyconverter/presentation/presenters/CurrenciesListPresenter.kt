package com.litil.currencyconverter.presentation.presenters

import android.content.ContentValues.TAG
import android.util.Log
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.usecases.GetCurrenciesUseCase
import com.litil.currencyconverter.presentation.views.CurrenciesListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrenciesListPresenter(private val getCurrenciesUseCase: GetCurrenciesUseCase): BasePresenter<CurrenciesListView>() {
    fun onViewResumed() {
        this.getCurrenciesUseCase()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {

            }
            .subscribe({
                it.valute?.values?.toList()?.let { it1 -> this.view?.bindCurrenciesList(it1) }
            }, {
                Log.e(TAG, it.toString())
            })
            .untilDestroyed()
    }
}