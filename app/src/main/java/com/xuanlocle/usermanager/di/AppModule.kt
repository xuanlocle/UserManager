package com.xuanlocle.usermanager.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xuanlocle.usermanager.BuildConfig
import com.xuanlocle.usermanager.data.source.remote.api.UserService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = Kodein.Module("appModule") {

    bind<OkHttpClient>() with singleton { createOkHttpClient() }

    bind<UserService>() with singleton {
        createWebService(okHttpClient = instance())
    }

}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String = BuildConfig.HOST,
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}


fun createOkHttpClient(): OkHttpClient {

    val client = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        client.addInterceptor(httpLoggingInterceptor)
    }

    return client.connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}
