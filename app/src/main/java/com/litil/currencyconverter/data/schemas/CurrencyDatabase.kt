package com.litil.currencyconverter.data.schemas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CurrencyDBModel::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context): CurrencyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                        CurrencyDatabase::class.java,
                    "currency"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}