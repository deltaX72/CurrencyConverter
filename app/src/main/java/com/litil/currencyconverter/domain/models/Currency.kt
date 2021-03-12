package com.litil.currencyconverter.domain.models

import com.google.gson.annotations.SerializedName

data class Currency(
        @SerializedName("Date")
        val date: String? = null,
        @SerializedName("PreviousDate")
        val prevDate: String? = null,
        @SerializedName("Valute")
        val currency: Map<String, Any>? = null,
        @SerializedName("Timestamp")
        val timestamp: String? = null
)
