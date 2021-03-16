package com.litil.currencyconverter.domain.usecases

import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.models.DailyCurrencyData
import com.litil.currencyconverter.domain.repositories.CurrencyRepository
import io.reactivex.Single

class GetHttpCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {
    operator fun invoke(): Single<DailyCurrencyData> {
        return this.currencyRepository.getCurrenciesList()
    }
}