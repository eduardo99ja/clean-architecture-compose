package com.apodaca.clean_architecture.data.network

class Token {
    companion object{
        @JvmStatic
        var token:String = ""
            set(value) {
                field = value
                retrofitInstance = null
            }

        @JvmStatic
        var retrofitInstance: ApiClient? = null

    }
}