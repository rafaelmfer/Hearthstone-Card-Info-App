package com.rafaelmfer.hearthstonecardinfoapp.data.network

import com.rafaelmfer.hearthstonecardinfoapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

private const val TIMEOUT_90 = 90L
private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/"

class HTTPClient {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader("x-rapidapi-host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                builder.addHeader("x-rapidapi-key", BuildConfig.API_KEY)
                return@Interceptor chain.proceed(builder.build())
            }
        )
        .readTimeout(TIMEOUT_90, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_90, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_90, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()


    private val provideRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <T : Any> create(clazz: KClass<T>): T = provideRetrofit.create(clazz.java)
}