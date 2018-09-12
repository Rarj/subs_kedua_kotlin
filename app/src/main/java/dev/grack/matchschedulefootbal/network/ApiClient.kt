package dev.grack.matchschedulefootbal.network

import dev.grack.matchschedulefootbal.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}