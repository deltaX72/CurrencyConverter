package com.litil.currencyconverter.domain.usecases

import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import io.reactivex.Single

class IsEmptyDatabaseCurrencyUseCase(private val repository: CurrencyDatabaseRepository) {
    operator fun invoke(): Single<Boolean> {
        return this.repository.isEmpty()
    }
}