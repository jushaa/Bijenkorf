package com.justin.ferron.bijenkorf.data.network.productdetail

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.justin.ferron.bijenkorf.data.network.ConnectivityInterceptor
import com.justin.ferron.bijenkorf.data.response.productdetail.ProductDetailResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Example query https://ceres-catalog.debijenkorf.nl/catalog/product/show?productCode=5098030010&productVariantCode=509803001012000

interface ProductDetailApiService {
    @GET("/catalog/product/show")
    fun getProductList(
        @Query("productCode") productCode: String,
        @Query("productVariantCode") productVariantCode: String
    ): Deferred<ProductDetailResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ProductDetailApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://ceres-catalog.debijenkorf.nl/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductDetailApiService::class.java)
        }
    }
}