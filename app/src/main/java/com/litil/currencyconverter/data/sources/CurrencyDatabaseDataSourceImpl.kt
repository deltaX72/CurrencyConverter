package com.litil.currencyconverter.data.sources

import com.litil.currencyconverter.data.schemas.Currency
import com.litil.currencyconverter.data.schemas.CurrencyDao
import com.litil.currencyconverter.data.sources.CurrencyDatabaseDataSource
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CurrencyDatabaseDataSourceImpl(private val currencyDao: CurrencyDao): CurrencyDatabaseDataSource {
    override fun getCurrency(charCode: String): Maybe<Currency> {
        return this.currencyDao.getCurrency(charCode).subscribeOn(Schedulers.io())
    }

    override fun addCurrency(currency: Currency): Completable {
        return this.currencyDao.addCurrency(currency).subscribeOn(Schedulers.io())
    }

    override fun updateCurrency(currency: Currency): Completable {
        return this.currencyDao.updateCurrency(currency).subscribeOn(Schedulers.io())
    }

    override fun deleteCurrency(currency: Currency): Completable {
        return this.currencyDao.deleteCurrency(currency).subscribeOn(Schedulers.io())
    }

    override fun deleteCurrency(charCode: String): Completable {
        return this.currencyDao.deleteCurrency(charCode).subscribeOn(Schedulers.io())
    }

    override fun getAll(): Single<List<Currency>> {
        return this.currencyDao.getAll().subscribeOn(Schedulers.io())
    }

    override fun getAllChosen(): Maybe<List<Currency>> {
        return this.currencyDao.getAllChosen().subscribeOn(Schedulers.io())
    }

    override fun setChosen(charCode: String, isChosen: Int): Completable {
        return this.currencyDao.setChosen(charCode, isChosen).subscribeOn(Schedulers.io())
    }
}