package com.justin.ferron.bijenkorf.ui.product.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.justin.ferron.bijenkorf.R
import com.justin.ferron.bijenkorf.data.network.ConnectivityInterceptorImplementation
import com.justin.ferron.bijenkorf.data.network.productlist.ProductApiService
import com.justin.ferron.bijenkorf.data.network.productlist.ProductNetworkDataSourceImplementation
import com.justin.ferron.bijenkorf.data.response.productlist.ProductListResponse
import com.justin.ferron.bijenkorf.ui.home.HomeScreenFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ProductListFragment : Fragment() {

    private lateinit var viewModel: ProductListViewModel

    companion object {
        fun newInstance() = ProductListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)

        //Gets the argument
        val arguments = arguments
        val searchString = arguments!!.getString("search")

        //Go to previous screen
        var productGoBackButton: Button? = view?.findViewById(R.id.productGoBackButton)
        productGoBackButton?.setOnClickListener {
            val fragment = HomeScreenFragment()
            fragment.arguments = arguments
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        //Get the ProductList JSON
        viewModel.getProductList(this.context!!, viewLifecycleOwner, searchString) {
            var textView: TextView? = view?.findViewById(R.id.userSearchedText)
            var recyclerView: RecyclerView? = view?.findViewById(R.id.productListView)
            recyclerView?.layoutManager = LinearLayoutManager(context)
            textView?.text = "Gezocht: " + it!!.data.searchText.original
            val jsonString = Gson().toJson(it)
            val gson = Gson().fromJson(jsonString, ProductListResponse::class.java)
            recyclerView?.adapter = ProductListAdapter(gson)

        }
    }
}