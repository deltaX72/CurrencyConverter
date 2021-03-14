package com.litil.currencyconverter.data.retrofit

import com.litil.currencyconverter.data.api.CurrencyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHolder {
    val retrofit = Retrofit.Builder().client(
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()
    ).addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://www.cbr-xml-daily.ru/")
        .build()

    val api by lazy {
        retrofit.create(CurrencyApi::class.java)
    }
}