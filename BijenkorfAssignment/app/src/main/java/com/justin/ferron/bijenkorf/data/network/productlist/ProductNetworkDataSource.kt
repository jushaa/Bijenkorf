package com.justin.ferron.bijenkorf.data.network.productlist

import androidx.lifecycle.LiveData
import com.justin.ferron.bijenkorf.data.response.productlist.ProductListResponse

interface ProductNetworkDataSource {

    val downloadedProductList: LiveData<ProductListResponse>

    suspend fun fetchProductList(search: String)
}