package com.litil.currencyconverter.presentation.presenters

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.litil.currencyconverter.data.converters.fromDatabaseCurrency
import com.litil.currencyconverter.data.schemas.CurrencyDBModel
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import com.litil.currencyconverter.domain.repositories.CurrencyRepository
import com.litil.currencyconverter.domain.usecases.CreateDatabaseCurrencyUseCase
import com.litil.currencyconverter.domain.usecases.GetDatabaseCurrenciesUseCase
import com.litil.currencyconverter.domain.usecases.GetHttpCurrenciesUseCase
import com.litil.currencyconverter.domain.usecases.IsEmptyDatabaseCurrencyUseCase
import com.litil.currencyconverter.presentation.views.CurrenciesListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.RuntimeException

class CurrenciesListPresenter(
    currencyRepository: CurrencyRepository,
    currencyDatabaseRepository: CurrencyDatabaseRepository,
    private val context: Context
): BasePresenter<CurrenciesListView>() {
    private val getDatabaseCurrenciesUseCase = GetDatabaseCurrenciesUseCase(currencyDatabaseRepository)
    private val createDatabaseCurrencyUseCase = CreateDatabaseCurrencyUseCase(currencyDatabaseRepository)
    private val getHttpCurrenciesUseCase = GetHttpCurrenciesUseCase(currencyRepository)
    private val isEmptyDatabaseCurrencyUseCase = IsEmptyDatabaseCurrencyUseCase(currencyDatabaseRepository)

    fun onViewResumed() {
        if (this.isDatabaseEmpty()) {
            val list = this.getCurrenciesList()
            if (list == null) {
                Toast.makeText(context, "ERROR! Cannot download data from the server!", Toast.LENGTH_LONG).show()
                return
            }
            this.loadToDatabase(list)
        }
        this.bindCurrenciesList()
    }

    private fun bindCurrenciesList() {
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

    private fun getCurrenciesList(): List<Currency>? {
        var list: List<Currency>? = null
        this.getHttpCurrenciesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list = it.valute?.values?.toList()
            }, {})
            .untilDestroyed()
        return list
    }

    private fun loadToDatabase(list: List<Currency>) {
        list.forEach { i ->
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

    private fun isDatabaseEmpty(): Boolean {
        var isEmpty = false
        this.isEmptyDatabaseCurrencyUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isEmpty = false
            }, {
                isEmpty = true
            })
            .untilDestroyed()
        return isEmpty
    }
}