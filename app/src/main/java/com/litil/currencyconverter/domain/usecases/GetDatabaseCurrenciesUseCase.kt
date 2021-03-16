package com.litil.currencyconverter.domain.usecases

import com.litil.currencyconverter.data.schemas.Currency
import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import io.reactivex.Single

class GetDatabaseCurrenciesUseCase(private val repository: CurrencyDatabaseRepository) {
    operator fun invoke(): Single<List<Currency>> {
        return this.repository.getAll()
    }
}