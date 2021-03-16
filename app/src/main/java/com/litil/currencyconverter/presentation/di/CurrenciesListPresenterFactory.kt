package com.litil.currencyconverter.presentation.di

import android.content.Context
import com.litil.currencyconverter.data.converters.toDatabaseCurrency
import com.litil.currencyconverter.data.repositories.CurrencyDatabaseRepositoryImpl
import com.litil.currencyconverter.data.sources.CurrencyHttpDataSourceImpl
import com.litil.currencyconverter.data.repositories.CurrencyRepositoryImpl
import com.litil.currencyconverter.data.retrofit.RetrofitHolder
import com.litil.currencyconverter.data.schemas.CurrencyDatabase
import com.litil.currencyconverter.data.sources.CurrencyDatabaseDataSource
import com.litil.currencyconverter.data.sources.CurrencyDatabaseDataSourceImpl
import com.litil.currencyconverter.domain.usecases.CreateDatabaseCurrencyUseCase
import com.litil.currencyconverter.domain.usecases.GetDatabaseCurrenciesUseCase
import com.litil.currencyconverter.domain.usecases.GetHttpCurrenciesUseCase
import com.litil.currencyconverter.presentation.presenters.CurrenciesListPresenter

object CurrenciesListPresenterFactory {
    fun create(context: Context): CurrenciesListPresenter {
        val currencyDataSource = CurrencyHttpDataSourceImpl(RetrofitHolder.api)
        val currencyRepository = CurrencyRepositoryImpl(currencyDataSource)

        val currencyDatabaseDataSource = CurrencyDatabaseDataSourceImpl(CurrencyDatabase.getDatabase(context).getCurrencyDao)
        val currencyDatabaseRepository = CurrencyDatabaseRepositoryImpl(currencyDatabaseDataSource)

        return CurrenciesListPresenter(
            currencyRepository,
            currencyDatabaseRepository,
            context
        )
    }
}