package com.justin.ferron.bijenkorf.data.network.productdetail

import androidx.lifecycle.LiveData
import com.justin.ferron.bijenkorf.data.response.productdetail.ProductDetailResponse

interface ProductDetailNetworkDataSource {


    val downloadedProductDetail: LiveData<ProductDetailResponse>

    suspend fun fetchProductDetail(productCode: String, productVariantCode: String)
}