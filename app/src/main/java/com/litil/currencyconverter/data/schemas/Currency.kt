package com.litil.currencyconverter.data.schemas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey
    val charCode: String,
    val name: String,
    val value: Double,
    val prevValue: Double,
    val isChosen: Boolean = false
)