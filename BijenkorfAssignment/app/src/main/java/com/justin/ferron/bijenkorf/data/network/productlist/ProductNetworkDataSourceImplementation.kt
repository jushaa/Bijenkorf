package com.justin.ferron.bijenkorf.data.network.productlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.justin.ferron.bijenkorf.data.response.productlist.ProductListResponse
import com.justin.ferron.bijenkorf.internal.NoConnectivityExceptionHandler

class ProductNetworkDataSourceImplementation(
    private val productApiService: ProductApiService
) : ProductNetworkDataSource {

    private val _downloadedProductList = MutableLiveData<ProductListResponse>()
    override val downloadedProductList: LiveData<ProductListResponse>
        get() = _downloadedProductList

    //This function fetches the product list with the search text
    override suspend fun fetchProductList(search: String) {
        try {
            val fetchProductList = productApiService.getProductList(search).await()
            _downloadedProductList.postValue(fetchProductList)
        } catch (e: NoConnectivityExceptionHandler) {
            Log.e("Error", "No connectivity", e)
        }
    }
}