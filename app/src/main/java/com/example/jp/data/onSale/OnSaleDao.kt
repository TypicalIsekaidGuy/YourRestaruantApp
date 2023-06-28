package com.example.jp.data.onSale

import androidx.room.Dao
import androidx.room.Query

@Dao
interface OnSaleDao {
    @Query("SELECT * FROM onsale ORDER BY id ASC")
    fun getAllProducts(): List<OnSale>
}