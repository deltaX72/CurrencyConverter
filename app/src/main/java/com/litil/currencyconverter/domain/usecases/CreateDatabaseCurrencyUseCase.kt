package com.litil.currencyconverter.domain.usecases

import com.litil.currencyconverter.data.schemas.CurrencyDBModel
import com.litil.currencyconverter.domain.repositories.CurrencyDatabaseRepository
import io.reactivex.Completable

class CreateDatabaseCurrencyUseCase(private val repository: CurrencyDatabaseRepository) {
    operator fun invoke(
        charCode: String,
        name: String,
        value: Double,
        prevValue: Double,
        isChosen: Boolean = false
    ): Completable {
        return this.repository.addCurrency(CurrencyDBModel(charCode, name, value, prevValue, isChosen))
    }
}