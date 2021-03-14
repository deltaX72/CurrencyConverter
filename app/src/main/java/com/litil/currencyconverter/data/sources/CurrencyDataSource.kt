package com.litil.currencyconverter.data.sources

import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.models.DailyCurrencyData
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyDataSource {
    fun getCurrenciesList(): Single<DailyCurrencyData>

    fun getCurrency(id: Long): Maybe<Currency>
}