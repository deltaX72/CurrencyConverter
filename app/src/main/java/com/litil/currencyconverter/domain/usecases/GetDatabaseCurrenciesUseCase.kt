package com.litil.currencyconverter.domain.usecases

import com.litil.currencyconverter.data.schemas.CurrencyDBModel
import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import io.reactivex.Single

class GetDatabaseCurrenciesUseCase(private val repository: CurrencyDatabaseRepository) {
    operator fun invoke(): Single<List<CurrencyDBModel>> {
        return this.repository.getAll()
    }
}