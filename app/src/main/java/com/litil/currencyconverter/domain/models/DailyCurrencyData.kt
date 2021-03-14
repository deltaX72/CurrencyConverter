package com.litil.currencyconverter.domain.models

import com.google.gson.annotations.SerializedName

data class DailyCurrencyData(
    @SerializedName("Date")
    val date: String? = null,
    @SerializedName("PreviousDate")
    val prevDate: String? = null,
    @SerializedName("Valute")
    val valute: Map<String, Currency>? = null,
    @SerializedName("Timestamp")
    val timestamp: String? = null
)
