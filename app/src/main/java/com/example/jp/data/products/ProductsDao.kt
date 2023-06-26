package com.example.jp.data.products

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAllProducts(): List<Products>
}