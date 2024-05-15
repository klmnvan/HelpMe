package com.example.matuleme.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileModel (
    var id: String? = "96c3c2f1-3d52-4e2d-bf90-aad8a4d93556",
    var name: String? = "",
    var surname: String? = "",
    var address: String? = "",
    var phone: String? = "",
    var email: String? = "",
)
