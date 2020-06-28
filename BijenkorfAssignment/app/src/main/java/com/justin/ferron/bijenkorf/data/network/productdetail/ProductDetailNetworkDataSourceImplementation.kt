package com.justin.ferron.bijenkorf.data.network.productdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.justin.ferron.bijenkorf.data.response.productdetail.ProductDetailResponse
import com.justin.ferron.bijenkorf.internal.NoConnectivityExceptionHandler

class ProductDetailNetworkDataSourceImplementation(
    private val productDetailApiService: ProductDetailApiService
) : ProductDetailNetworkDataSource {

    private val _downloadedProductDetail = MutableLiveData<ProductDetailResponse>()
    override val downloadedProductDetail: LiveData<ProductDetailResponse>
        get() = _downloadedProductDetail

    //This function fetches the product detail with productCode and productVariantCode
    override suspend fun fetchProductDetail(productCode: String, productVariantCode: String) {
        try {
            val fetchProductList =
                productDetailApiService.getProductList(productCode, productVariantCode).await()
            _downloadedProductDetail.postValue(fetchProductList)
        } catch (e: NoConnectivityExceptionHandler) {
            Log.e("Error", "No connectivity", e)
        }
    }
}