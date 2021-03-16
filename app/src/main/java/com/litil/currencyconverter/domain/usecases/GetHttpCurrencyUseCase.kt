package com.litil.currencyconverter.domain.usecases

import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.repositories.CurrencyRepository
import io.reactivex.Maybe
import io.reactivex.Single

class GetHttpCurrencyUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(id: Long): Maybe<Currency> {
        return this.currencyRepository.getCurrency(id)
    }
}