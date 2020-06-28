package com.justin.ferron.bijenkorf.ui.product.list

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.justin.ferron.bijenkorf.R
import com.justin.ferron.bijenkorf.data.network.ConnectivityInterceptorImplementation
import com.justin.ferron.bijenkorf.data.network.productlist.ProductApiService
import com.justin.ferron.bijenkorf.data.network.productlist.ProductNetworkDataSourceImplementation
import com.justin.ferron.bijenkorf.data.response.productdetail.ProductDetailResponse
import com.justin.ferron.bijenkorf.data.response.productlist.ProductListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductListViewModel(
) : ViewModel() {
    fun getProductList(context: Context, viewLifecycleOwner: LifecycleOwner, search: String, callback: (result: ProductListResponse?) -> Unit){
        val productApiService =
            ProductApiService(
                ConnectivityInterceptorImplementation(context)
            )
        val productNetworkDataSource =
            ProductNetworkDataSourceImplementation(
                productApiService
            )
        productNetworkDataSource.downloadedProductList.observe(viewLifecycleOwner, Observer {
            callback.invoke(it)
        })
        GlobalScope.launch(Dispatchers.Main) {
            productNetworkDataSource.fetchProductList(search)
        }
    }
}