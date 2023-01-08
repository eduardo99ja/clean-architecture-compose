package com.apodaca.clean_architecture.domain.model

import com.apodaca.clean_architecture.data.model.UserModel
import com.google.gson.annotations.SerializedName

data class User(
    var name: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var apiToken: String? = null,
)

fun UserModel.toDomain(): User = User(
    name = name,
    lastName = lastName,
    email = email,
    apiToken = apiToken,
)