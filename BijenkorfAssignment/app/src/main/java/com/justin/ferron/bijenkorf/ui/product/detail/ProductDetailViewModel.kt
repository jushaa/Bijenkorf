package com.justin.ferron.bijenkorf.ui.product.detail
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.justin.ferron.bijenkorf.data.network.ConnectivityInterceptorImplementation
import com.justin.ferron.bijenkorf.data.network.productdetail.ProductDetailApiService
import com.justin.ferron.bijenkorf.data.network.productdetail.ProductDetailNetworkDataSourceImplementation
import com.justin.ferron.bijenkorf.data.response.productdetail.ProductDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProductDetailViewModel : ViewModel() {

     var response : ProductDetailResponse? = null

     fun getProductDetail(context: Context, viewLifecycleOwner: LifecycleOwner, productCode: String, productVariantCode: String, callback: (result: ProductDetailResponse?) -> Unit) {
        val productDetailApiService =
            ProductDetailApiService(
                ConnectivityInterceptorImplementation(context)
            )
        val productDetailNetworkDataSource =
            ProductDetailNetworkDataSourceImplementation(
                productDetailApiService
            )
        productDetailNetworkDataSource.downloadedProductDetail.observe(viewLifecycleOwner, Observer {
            callback.invoke(it)
        })

        GlobalScope.launch(Dispatchers.Main) {
            productDetailNetworkDataSource.fetchProductDetail(productCode, productVariantCode)
        }
    }
}