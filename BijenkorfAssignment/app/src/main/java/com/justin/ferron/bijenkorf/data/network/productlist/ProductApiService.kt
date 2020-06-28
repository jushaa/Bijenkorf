package com.justin.ferron.bijenkorf.data.network.productlist

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.justin.ferron.bijenkorf.data.network.ConnectivityInterceptor
import com.justin.ferron.bijenkorf.data.response.productlist.ProductListResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//Example query https://ceres-catalog.debijenkorf.nl/catalog/navigation/search?text=trui

interface ProductApiService {

    @GET("/catalog/navigation/search")
    fun getProductList(
        @Query("text") search: String
    ): Deferred<ProductListResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ProductApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://ceres-catalog.debijenkorf.nl/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductApiService::class.java)


        }
    }
}