package com.litil.currencyconverter.domain.repositories

import com.litil.currencyconverter.data.schemas.CurrencyDBModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface CurrencyDatabaseRepository {
    fun getCurrency(charCode: String): Maybe<CurrencyDBModel>

    fun addCurrency(currencyDBModel: CurrencyDBModel): Completable

    fun updateCurrency(currencyDBModel: CurrencyDBModel): Completable

    fun deleteCurrency(currencyDBModel: CurrencyDBModel): Completable

    fun deleteCurrency(charCode: String): Completable

    fun getAll(): Single<List<CurrencyDBModel>>

    fun getAllChosen(): Maybe<List<CurrencyDBModel>>

    fun setChosen(charCode: String, isChosen: Boolean): Completable

    fun isEmpty(): Single<Int>
}