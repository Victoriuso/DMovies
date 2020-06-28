package com.binarproject.tmdb.utils

import com.binarproject.tmdb.apis.IJsonApi
import com.binarproject.tmdb.strings.URLCollections
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConnectionUtils {

    companion object {
        fun getApi(token: String?): IJsonApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            val builder : Retrofit.Builder = Retrofit.Builder()
                .baseUrl(URLCollections.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())

            //Add token authorization if token is not null
            token?.let {
                builder.client(OkHttpClient.Builder().addInterceptor { chain ->
                        val request =
                            chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer ${it}")
                                .build()
                        chain.proceed(request)
                    }.build())
            }

            val api = builder.build()
            val service: IJsonApi = api.create(IJsonApi::class.java)
            return service
        }
    }
}