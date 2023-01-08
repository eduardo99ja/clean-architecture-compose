package com.apodaca.clean_architecture.data.network

object ApiConstants {
    const val serverPath = "https://platcapandroid-web.dev.satoritech.com.mx"
    const val localPath = "http://10.0.2.2:8000"
    const val serverPathProduction = ""
    const val wsPath = "/api/"

    private const val production: Boolean = false
    private const val localhost: Boolean = true

    fun getServerPath(): String {
        if(localhost) return localPath
        return if (production) serverPathProduction else serverPath
    }
}