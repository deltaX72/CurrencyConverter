package com.litil.currencyconverter.data.repositories

import com.litil.currencyconverter.data.sources.CurrencyHttpDataSource
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.models.DailyCurrencyData
import com.litil.currencyconverter.domain.repositories.CurrencyRepository
import io.reactivex.Maybe
import io.reactivex.Single

class CurrencyRepositoryImpl(private val currencyHttpDataSource: CurrencyHttpDataSource): CurrencyRepository {
    override fun getCurrenciesList(): Single<DailyCurrencyData> {
        return this.currencyHttpDataSource.getCurrenciesList()
    }

    override fun getCurrency(id: Long): Maybe<Currency> {
        return this.currencyHttpDataSource.getCurrency(id)
    }
}