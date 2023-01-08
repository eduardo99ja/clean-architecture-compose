package com.apodaca.clean_architecture.di

import com.apodaca.clean_architecture.data.network.ApiClient
import com.apodaca.clean_architecture.data.network.ApiConstants
import com.apodaca.clean_architecture.data.network.ApiService
import com.apodaca.clean_architecture.data.network.Token
import com.apodaca.clean_architecture.data.network.serializers.BooleanDeserializer
import com.apodaca.clean_architecture.data.network.serializers.BooleanSerializer
import com.apodaca.clean_architecture.data.network.serializers.DateDeserializer
import com.apodaca.clean_architecture.data.network.serializers.DateSerializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideRetrofit(): ApiClient {
        if(Token.retrofitInstance == null){
            Token.retrofitInstance = Retrofit.Builder()
                .baseUrl(ApiConstants.getServerPath())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .client(provideHttpClient())
                .build()
                .create(ApiClient::class.java)
        }
        return Token.retrofitInstance!!
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor

    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor())
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + Token.token)
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    private fun gson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, BooleanSerializer())
            .registerTypeAdapter(Boolean::class.java, BooleanDeserializer())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanSerializer())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanDeserializer())
            .registerTypeAdapter(Date::class.java, DateSerializer())
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()
    }

}