package com.justin.ferron.bijenkorf.ui.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.justin.ferron.bijenkorf.R
import com.justin.ferron.bijenkorf.data.response.productlist.Product
import com.justin.ferron.bijenkorf.data.response.productlist.ProductListResponse
import com.justin.ferron.bijenkorf.ui.product.detail.ProductDetailFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list_row.view.*


class ProductListAdapter(private val productList: ProductListResponse) :
    RecyclerView.Adapter<CustomViewHolder>() {

    //TODO outside of assignment scope: Pagination or next page to load next 20
    //Get item count makes sure we will only show 20 products
    override fun getItemCount(): Int {
        return if (productList.data.products.count() > 20) {
            20
        } else {
            productList.data.products.count()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.product_list_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    //Binds Data to ViewHolder
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val products = productList.data.products[position]
        holder.view.productBrandName.text = products.brand.name
        holder.view.productName.text = products.name
        val productPriceString = "€ " + products.currentVariantProduct.sellingPrice.value.toString()
        holder.view.productPrice.text = productPriceString
        val imageString = products.currentVariantProduct.images[0].url
        val subImageString: String = imageString.substring(imageString.indexOf("cdn"))
        val newImageString = "https://$subImageString"
        val productPhoto = holder.view.productImage
        Picasso.get().load(newImageString).into(productPhoto);
        holder.products = products
        holder.searchText = productList.data.searchText.original

    }
}

//The CustomViewHolder loads ProductDetailFragment with the product from the ViewHolder
class CustomViewHolder(
    val view: View,
    var searchText: String? = null,
    var products: Product? = null
) : RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val arguments = Bundle()
            arguments.putString("search", searchText)
            arguments.putString("productCode", products?.code)
            arguments.putString("productVariantCode", products?.currentVariantProduct?.code)
            val activity = view.context as AppCompatActivity
            val myFragment: Fragment = ProductDetailFragment()
            myFragment.arguments = arguments
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, myFragment).addToBackStack(null).commit()
        }
    }

}
