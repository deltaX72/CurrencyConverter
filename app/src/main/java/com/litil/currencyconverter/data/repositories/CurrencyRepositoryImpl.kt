package com.litil.currencyconverter.data.repositories

import com.litil.currencyconverter.data.sources.CurrencyDataSource
import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.models.DailyCurrencyData
import com.litil.currencyconverter.domain.repositories.CurrencyRepository
import io.reactivex.Maybe
import io.reactivex.Single

class CurrencyRepositoryImpl(private val currencyDataSource: CurrencyDataSource): CurrencyRepository {
    override fun getCurrenciesList(): Single<DailyCurrencyData> {
        return this.currencyDataSource.getCurrenciesList()
    }

    override fun getCurrency(id: Long): Maybe<Currency> {
        return this.currencyDataSource.getCurrency(id)
    }
}