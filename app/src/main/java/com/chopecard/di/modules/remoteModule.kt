package com.chopecard.di.modules


import FakeJsonConf
import com.chopecard.data.network.StoreApiService
import com.chopecard.data.network.UserApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Module where all the remote (network) classes to inject must be declared
 */
internal val remoteModule = module {
    single(named(fakeApiRetrofitClient)) { createRetrofitClient(get(), get<FakeJsonConf>().baseUrl) }

    single { createOkHttpClient() }

    single {
        createWebService<StoreApiService>(
            get(named(fakeApiRetrofitClient))
        )
    }

    single {
        createWebService<UserApiService>(
            get(named(fakeApiRetrofitClient))
        )
    }
}

fun createOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()
}

fun createRetrofitClient(okhttpClient: OkHttpClient, baseUrl: String): Retrofit {
    val gsonConverter =
        GsonConverterFactory.create(
            GsonBuilder().create()
        )

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okhttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(gsonConverter)
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

const val fakeApiRetrofitClient = "fakeApiRetrofitClient"
