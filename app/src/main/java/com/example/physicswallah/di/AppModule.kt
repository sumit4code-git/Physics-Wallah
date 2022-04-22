package com.example.physicswallah.di

import com.example.physicswallah.api.TeachersApi
import com.example.socialbubble.constants.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideUsersApi():TeachersApi{
        val httpLoggingInterceptor= HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
        okHttpClientBuilder.addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .build()
            val response = chain.proceed(newRequest)
            response
        }
        val okHttpClient=okHttpClientBuilder.build()

       return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
           .client(okHttpClient)
            .build()
            .create(TeachersApi::class.java)
    }

}