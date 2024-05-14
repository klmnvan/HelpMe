package com.example.matuleme.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderModel (
    var id: String? = "",
    var tittle: String? = "",
    var price1: Int? = 0,
    var price2: Int? = 0,
    var data: String? = "",
)
