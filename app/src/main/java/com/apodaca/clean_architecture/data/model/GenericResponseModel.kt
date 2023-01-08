package com.apodaca.clean_architecture.data.model

data class GenericResponseModel<T>(
    var success: Boolean = false,
    var message: String = "",
    var code: Int = 404,
    var data: T?
)