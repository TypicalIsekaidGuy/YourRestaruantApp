package com.example.jp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAllProducts(): List<Products>
}