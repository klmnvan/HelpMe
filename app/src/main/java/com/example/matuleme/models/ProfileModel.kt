package com.example.matuleme.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileModel (
    var id: String? = "",
    var name: String? = "",
    var surname: String? = "",
    var address: String? = "",
    var phone: String? = "",
    var email: String? = "",
)
