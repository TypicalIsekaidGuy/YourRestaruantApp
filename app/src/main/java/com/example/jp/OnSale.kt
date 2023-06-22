package com.example.jp

import androidx.annotation.DrawableRes

data class OnSale(
    val title: String,
    @DrawableRes val imageId: Int,
    val description: String
)
