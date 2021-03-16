package com.litil.currencyconverter.data.sources

import com.litil.currencyconverter.data.schemas.Currency
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyDatabaseDataSource {
    fun getCurrency(charCode: String): Maybe<Currency>

    fun addCurrency(currency: Currency): Completable

    fun updateCurrency(currency: Currency): Completable

    fun deleteCurrency(currency: Currency): Completable

    fun deleteCurrency(charCode: String): Completable

    fun getAll(): Single<List<Currency>>

    fun getAllChosen(): Maybe<List<Currency>>

    fun setChosen(charCode: String, isChosen: Int): Completable
}