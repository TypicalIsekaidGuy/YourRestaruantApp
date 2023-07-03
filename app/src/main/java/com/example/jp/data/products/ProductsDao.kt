package com.example.jp.data.products

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface ProductsDao {
    @Query("SELECT type, id FROM products")
    fun getChips(): List<ChipTuple>
    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAllProducts(): List<Products>

}
data class ChipTuple(
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "id") val id: Int
)