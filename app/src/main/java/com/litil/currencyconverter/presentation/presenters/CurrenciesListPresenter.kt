package com.litil.currencyconverter.presentation.presenters

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
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
    private val getHttpCurrenciesUseCase: GetHttpCurrenciesUseCase,
    private val getDatabaseCurrenciesUseCase: GetDatabaseCurrenciesUseCase,
    private val createDatabaseCurrencyUseCase: CreateDatabaseCurrencyUseCase,
    private val context: Context
): BasePresenter<CurrenciesListView>() {
    val listCurrency: MutableList<Currency> = emptyList<Currency>().toMutableList()

    fun onViewResumed() {
        this.getDatabaseCurrenciesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.getCurrenciesList()
                if (this.getRows() == 0) {
                    this.loadToDatabase(this.listCurrency)
                }
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

    fun getCurrenciesList() {
        this.getHttpCurrenciesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.listCurrency.add(
                    Currency(
                        charCode = "RUB",
                        name = "Российский рубль",
                        value = 1.0,
                        prevValue = 1.0
                    )
                )
                for (i in it.valute?.values!!) {
                    this.listCurrency.add(i)
                }
                Log.e("Hahahahahahahahhahahahahaha", this.listCurrency.toString())
            }, {})
            .untilDestroyed()
    }

    fun loadToDatabase(list: MutableList<Currency>) {
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

    fun getRows(): Int {
        var rows = 0
        this.getDatabaseCurrenciesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                rows = it.count()
            }, {})
            .untilDestroyed()
        return rows
    }
}