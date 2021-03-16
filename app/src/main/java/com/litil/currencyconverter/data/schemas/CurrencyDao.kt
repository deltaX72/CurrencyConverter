package com.litil.currencyconverter.data.schemas

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrency(currency: Currency): Completable

    @Update(entity = Currency::class)
    fun updateCurrency(currency: Currency): Completable

    @Query("SELECT * FROM currency")
    fun getAll(): Single<List<Currency>>

    @Query("SELECT * FROM currency WHERE charCode = :charCode")
    fun getCurrency(charCode: String): Maybe<Currency>

    @Delete(entity = Currency::class)
    fun deleteCurrency(currency: Currency): Completable

    @Query("DELETE FROM currency WHERE charCode = :charCode")
    fun deleteCurrency(charCode: String): Completable

    @Query("SELECT * FROM currency WHERE isChosen = 1")
    fun getAllChosen(): Maybe<List<Currency>>

    @Query("UPDATE currency SET isChosen = :isChosen WHERE charCode = :charCode")
    fun setChosen(charCode: String, isChosen: Int): Completable
}