package com.apodaca.clean_architecture.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(


    var id: Long? = null,

    var name: String? = null,

    @SerializedName("last_name")
    var lastName: String? = null,


    var phone: String? = null,

    var email: String? = null,

    var password: String? = null,

    @SerializedName("image_url")
    var imgUrl: String? = null,

    @SerializedName("api_token")
    var apiToken: String? = null,

    )