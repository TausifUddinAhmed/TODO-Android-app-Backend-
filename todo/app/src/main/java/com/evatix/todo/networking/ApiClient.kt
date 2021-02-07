package com.evatix.todo.networking


import com.evatix.todo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient{


    fun provideApi(): ApiInterfaceService {
        return provideRetrofit().create(ApiInterfaceService::class.java)
    }


    private fun provideRetrofit(): Retrofit {



        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(140, TimeUnit.SECONDS)
            .readTimeout(140, TimeUnit.SECONDS)
            .writeTimeout(140, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




}