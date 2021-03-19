package com.litil.currencyconverter.data.sources

import com.litil.currencyconverter.data.schemas.CurrencyDBModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyDatabaseDataSource {
    fun getCurrency(charCode: String): Maybe<CurrencyDBModel>

    fun addCurrency(currencyDBModel: CurrencyDBModel): Completable

    fun updateCurrency(currencyDBModel: CurrencyDBModel): Completable

    fun deleteCurrency(currencyDBModel: CurrencyDBModel): Completable

    fun deleteCurrency(charCode: String): Completable

    fun getAll(): Single<List<CurrencyDBModel>>

    fun getAllChosen(): Maybe<List<CurrencyDBModel>>

    fun setChosen(charCode: String, isChosen: Int): Completable

    fun isEmpty(): Single<Int>
}