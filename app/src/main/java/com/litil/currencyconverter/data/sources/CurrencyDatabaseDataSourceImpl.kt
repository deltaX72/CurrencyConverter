package com.litil.currencyconverter.data.sources

import com.litil.currencyconverter.data.schemas.CurrencyDBModel
import com.litil.currencyconverter.data.schemas.CurrencyDao
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CurrencyDatabaseDataSourceImpl(private val currencyDao: CurrencyDao): CurrencyDatabaseDataSource {
    override fun getCurrency(charCode: String): Maybe<CurrencyDBModel> {
        return this.currencyDao.getCurrency(charCode).subscribeOn(Schedulers.io())
    }

    override fun addCurrency(currencyDBModel: CurrencyDBModel): Completable {
        return this.currencyDao.addCurrency(currencyDBModel).subscribeOn(Schedulers.io())
    }

    override fun updateCurrency(currencyDBModel: CurrencyDBModel): Completable {
        return this.currencyDao.updateCurrency(currencyDBModel).subscribeOn(Schedulers.io())
    }

    override fun deleteCurrency(currencyDBModel: CurrencyDBModel): Completable {
        return this.currencyDao.deleteCurrency(currencyDBModel).subscribeOn(Schedulers.io())
    }

    override fun deleteCurrency(charCode: String): Completable {
        return this.currencyDao.deleteCurrency(charCode).subscribeOn(Schedulers.io())
    }

    override fun getAll(): Single<List<CurrencyDBModel>> {
        return this.currencyDao.getAll().subscribeOn(Schedulers.io())
    }

    override fun getAllChosen(): Maybe<List<CurrencyDBModel>> {
        return this.currencyDao.getAllChosen().subscribeOn(Schedulers.io())
    }

    override fun setChosen(charCode: String, isChosen: Int): Completable {
        return this.currencyDao.setChosen(charCode, isChosen).subscribeOn(Schedulers.io())
    }

    override fun isEmpty(): Single<Boolean> {
        return this.currencyDao.isEmpty().subscribeOn(Schedulers.io())
    }
}