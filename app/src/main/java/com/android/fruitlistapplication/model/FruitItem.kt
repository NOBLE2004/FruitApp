package com.android.fruitlistapplication.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class FruitItem(

    @field:SerializedName("price")
    @ColumnInfo(name = "price") val price: Float,

    @field:SerializedName("weight")
    @ColumnInfo(name = "weight") val weight: Float,

    @field:SerializedName("type")
    @ColumnInfo(name = "type") val type: String,

    @ColumnInfo(name = "priceKg") val priceKg: Float,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int
) : Serializable