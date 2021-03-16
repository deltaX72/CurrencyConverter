package com.litil.currencyconverter.data.schemas

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrency(currencyDBModel: CurrencyDBModel): Completable

    @Update(entity = CurrencyDBModel::class)
    fun updateCurrency(currencyDBModel: CurrencyDBModel): Completable

    @Query("SELECT * FROM currency")
    fun getAll(): Single<List<CurrencyDBModel>>

    @Query("SELECT * FROM currency WHERE charCode = :charCode")
    fun getCurrency(charCode: String): Maybe<CurrencyDBModel>

    @Delete(entity = CurrencyDBModel::class)
    fun deleteCurrency(currencyDBModel: CurrencyDBModel): Completable

    @Query("DELETE FROM currency WHERE charCode = :charCode")
    fun deleteCurrency(charCode: String): Completable

    @Query("SELECT * FROM currency WHERE isChosen = 1")
    fun getAllChosen(): Maybe<List<CurrencyDBModel>>

    @Query("UPDATE currency SET isChosen = :isChosen WHERE charCode = :charCode")
    fun setChosen(charCode: String, isChosen: Int): Completable

    @Query("SELECT COUNT(*) FROM currency")
    fun isEmpty(): Single<Boolean>
}