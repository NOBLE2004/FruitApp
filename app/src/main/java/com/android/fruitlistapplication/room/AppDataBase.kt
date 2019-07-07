package com.android.fruitlistapplication.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.android.fruitlistapplication.model.FruitItem

@Database(entities = arrayOf(FruitItem::class), version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun FruitDao(): FruitDao
}