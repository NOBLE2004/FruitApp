package com.android.fruitlistapplication.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.android.fruitlistapplication.model.FruitItem
import io.reactivex.Maybe

@Dao
interface FruitDao {
    @Query("select * from FruitItem")
    fun getAll(): Maybe<List<FruitItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg fruitdata: FruitItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(fruitdata: List<FruitItem>)
}