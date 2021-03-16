package com.litil.currencyconverter.data.repositories

import com.litil.currencyconverter.data.schemas.Currency
import com.litil.currencyconverter.data.sources.CurrencyDatabaseDataSource
import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class CurrencyDatabaseRepositoryImpl(private val source: CurrencyDatabaseDataSource): CurrencyDatabaseRepository {
    override fun getCurrency(charCode: String): Maybe<Currency> {
        return this.source.getCurrency(charCode)
    }

    override fun addCurrency(currency: Currency): Completable {
        return this.source.addCurrency(currency)
    }

    override fun updateCurrency(currency: Currency): Completable {
        return this.source.updateCurrency(currency)
    }

    override fun deleteCurrency(currency: Currency): Completable {
        return this.source.deleteCurrency(currency)
    }

    override fun deleteCurrency(charCode: String): Completable {
        return this.source.deleteCurrency(charCode)
    }

    override fun getAll(): Single<List<Currency>> {
        return this.source.getAll()
    }

    override fun getAllChosen(): Maybe<List<Currency>> {
        return this.source.getAllChosen()
    }

    override fun setChosen(charCode: String, isChosen: Boolean): Completable {
        return this.source.setChosen(charCode, if (isChosen) 1 else 0)
    }
}