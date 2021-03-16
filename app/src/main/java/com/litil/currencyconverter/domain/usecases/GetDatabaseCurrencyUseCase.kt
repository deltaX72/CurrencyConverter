package com.litil.currencyconverter.domain.usecases

import com.litil.currencyconverter.data.schemas.Currency
import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import io.reactivex.Completable
import io.reactivex.Maybe

class GetDatabaseCurrencyUseCase(private val repository: CurrencyDatabaseRepository) {
    operator fun invoke(charCode: String): Maybe<Currency> {
        return this.repository.getCurrency(charCode)
    }
}