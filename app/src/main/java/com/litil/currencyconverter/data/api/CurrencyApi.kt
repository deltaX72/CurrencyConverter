package com.litil.currencyconverter.data.api

import com.litil.currencyconverter.domain.models.Currency
import com.litil.currencyconverter.domain.models.DailyCurrencyData
import com.litil.currencyconverter.domain.models.Response
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    fun getCurrenciesList(): Single<DailyCurrencyData>
}