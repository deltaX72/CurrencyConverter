package com.litil.currencyconverter.domain.repositories

import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.models.DailyCurrencyData
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyRepository {
    fun getCurrenciesList(): Single<DailyCurrencyData>

    fun getCurrency(id: Long): Maybe<Currency>
}