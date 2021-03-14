package com.litil.currencyconverter.domain.models

import com.google.gson.annotations.SerializedName

data class Currency(
        val id: Long = -1,
        @SerializedName("Name")
        val name: String = "Российский рубль",
        @SerializedName("CharCode")
        val charCode: String = "RUB",
        @SerializedName("Value")
        val value: Double = 1.0,
        @SerializedName("Previous")
        val prevValue: Double = 1.0
)
