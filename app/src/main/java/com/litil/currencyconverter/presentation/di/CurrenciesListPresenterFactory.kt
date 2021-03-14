package com.litil.currencyconverter.presentation.di

import com.litil.currencyconverter.data.repositories.CurrencyDataSourceImpl
import com.litil.currencyconverter.data.repositories.CurrencyRepositoryImpl
import com.litil.currencyconverter.data.retrofit.RetrofitHolder
import com.litil.currencyconverter.domain.usecases.GetCurrenciesUseCase
import com.litil.currencyconverter.presentation.presenters.CurrenciesListPresenter
import retrofit2.Retrofit

object CurrenciesListPresenterFactory {
    fun create(): CurrenciesListPresenter {
        val currencyDataSource = CurrencyDataSourceImpl(RetrofitHolder.api)
        val currencyRepository = CurrencyRepositoryImpl(currencyDataSource)
        val getCurrenciesUseCase = GetCurrenciesUseCase(currencyRepository)
        return CurrenciesListPresenter(getCurrenciesUseCase)
    }
}