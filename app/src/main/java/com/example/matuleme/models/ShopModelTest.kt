package com.example.matuleme.models

import kotlinx.serialization.Serializable

@Serializable
data class ShopModelTest(
    var isFavourit: Boolean? = false,
    var name: String? = "",
    var description: String? = "",
    var price: Int? = 0,
    var category: String = ""
)
