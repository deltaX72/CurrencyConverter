package com.litil.currencyconverter.data.sources

import com.litil.currencyconverter.data.api.CurrencyApi
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.models.DailyCurrencyData
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CurrencyHttpDataSourceImpl(private val api: CurrencyApi): CurrencyHttpDataSource {
    override fun getCurrenciesList(): Single<DailyCurrencyData> {
        return this.api.getCurrenciesList().subscribeOn(Schedulers.io())
    }

    override fun getCurrency(id: Long): Maybe<Currency> {
        return this.api.getCurrenciesList()
            .flatMapMaybe {
                val currency = it.valute?.values?.find { it.id == id }
                if (currency == null) {
                    Maybe.error(Throwable("No such currency!"))
                } else {
                    Maybe.just(currency)
                }
            }
            .subscribeOn(Schedulers.io())
    }
}