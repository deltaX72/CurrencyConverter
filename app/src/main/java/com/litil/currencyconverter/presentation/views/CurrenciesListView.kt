package com.litil.currencyconverter.presentation.views

import com.litil.currencyconverter.domain.models.Currency

interface CurrenciesListView: BaseView {
    fun bindCurrenciesList(list: List<Currency>)
}