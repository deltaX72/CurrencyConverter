package com.litil.currencyconverter.data.repositories

import com.litil.currencyconverter.data.schemas.CurrencyDBModel
import com.litil.currencyconverter.data.sources.CurrencyDatabaseDataSource
import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class CurrencyDatabaseRepositoryImpl(private val source: CurrencyDatabaseDataSource): CurrencyDatabaseRepository {
    override fun getCurrency(charCode: String): Maybe<CurrencyDBModel> {
        return this.source.getCurrency(charCode)
    }

    override fun addCurrency(currencyDBModel: CurrencyDBModel): Completable {
        return this.source.addCurrency(currencyDBModel)
    }

    override fun updateCurrency(currencyDBModel: CurrencyDBModel): Completable {
        return this.source.updateCurrency(currencyDBModel)
    }

    override fun deleteCurrency(currencyDBModel: CurrencyDBModel): Completable {
        return this.source.deleteCurrency(currencyDBModel)
    }

    override fun deleteCurrency(charCode: String): Completable {
        return this.source.deleteCurrency(charCode)
    }

    override fun getAll(): Single<List<CurrencyDBModel>> {
        return this.source.getAll()
    }

    override fun getAllChosen(): Maybe<List<CurrencyDBModel>> {
        return this.source.getAllChosen()
    }

    override fun setChosen(charCode: String, isChosen: Boolean): Completable {
        return this.source.setChosen(charCode, if (isChosen) 1 else 0)
    }

    override fun isEmpty(): Single<Int> {
        return this.source.isEmpty()
    }
}