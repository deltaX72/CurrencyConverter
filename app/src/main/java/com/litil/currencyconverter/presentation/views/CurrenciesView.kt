package com.litil.currencyconverter.presentation.views

import com.litil.currencyconverter.domain.models.Currency

interface CurrenciesView: BaseView {
    fun bindCurrenciesList(list: List<Currency>)
}