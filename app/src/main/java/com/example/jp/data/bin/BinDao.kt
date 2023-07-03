package com.example.jp.data.bin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.jp.data.products.Bin

@Dao
interface BinDao {
    @Insert
    fun insertProduct(bin: Bin)
    @Delete
    fun deleteProduct(bin: Bin)
    @Update
    fun updateProduct(bin: Bin)
    @Query("SELECT * FROM Bin ORDER BY id ASC")
    fun getAllProducts(): List<Bin>
}