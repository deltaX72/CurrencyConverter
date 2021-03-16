package com.litil.currencyconverter.data.converters

import com.litil.currencyconverter.domain.models.Currency

fun Currency.toDatabaseCurrency(): com.litil.currencyconverter.data.schemas.Currency {
    return com.litil.currencyconverter.data.schemas.Currency(
        charCode = this.charCode,
        name = this.name,
        value = this.value,
        prevValue = this.prevValue
    )
}


fun com.litil.currencyconverter.data.schemas.Currency.fromDatabaseCurrency(): Currency {
    return Currency(
        charCode = this.charCode,
        name = this.name,
        value = this.value,
        prevValue = this.prevValue
    )
}