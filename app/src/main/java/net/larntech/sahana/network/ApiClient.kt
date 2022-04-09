package net.larntech.sahana.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {


    private fun getRetrofit(): Retrofit {
        var client : OkHttpClient?;
        val logger = HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build();


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.npoint.io/")
            .client(client)
            .build();

    }


    fun getApiService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }


}