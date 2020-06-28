package com.justin.ferron.bijenkorf.ui.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.justin.ferron.bijenkorf.R
import com.justin.ferron.bijenkorf.ui.product.list.ProductListFragment
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            ProductDetailFragment()
    }

    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductDetailViewModel::class.java)
        //Init textviews & image & button
        var productDetailImage: ImageView? = view?.findViewById(R.id.productDetailImage)
        var productDetailBrandName: TextView? = view?.findViewById(R.id.productDetailBrandName)
        var productDetailName: TextView? = view?.findViewById(R.id.productDetailName)
        var productDetailPrice: TextView? = view?.findViewById(R.id.productDetailPrice)
        var productDetailDescColor: TextView? = view?.findViewById(R.id.productDetailDescColor)
        var productDetailDescDeliveryTime: TextView? =
            view?.findViewById(R.id.productDetailDescDeliveryTime)
        var productDetailGoBackButton: Button? = view?.findViewById(R.id.productDetailGoBackButton)

        //Go to previous screen with setOnClickListener
        productDetailGoBackButton?.setOnClickListener {
            val searchText = arguments!!.getString("search")
            val arguments = Bundle()
            arguments.putString("search", searchText)
            val fragment = ProductListFragment()
            fragment.arguments = arguments
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        //Get the product detail JSON
        val productCode = arguments!!.getString("productCode")
        val productVariantCode = arguments!!.getString("productVariantCode")
        viewModel.getProductDetail(this.context!!, viewLifecycleOwner , productCode, productVariantCode) {
            if(it != null){
                val imageString = it.data.product.currentVariantProduct.images[0].url
                val subImageString: String = imageString.substring(imageString.indexOf("cdn"))
                val newImageString = "https://$subImageString"
                Picasso.get().load(newImageString).into(productDetailImage)

                productDetailBrandName?.text = it.data.product.brand.name
                productDetailName?.text = it.data.product.name
                val productPriceString = "€ " + it.data.product.currentVariantProduct.sellingPrice.value.toString()
                productDetailPrice?.text = productPriceString
                val productDescColorString = "Kleur: " + it.data.product.currentVariantProduct.color
                productDetailDescColor?.text = productDescColorString
                val productDescDeliveryTime = "Levertijd: " + it.data.product.currentVariantProduct.deliveryTime
                productDetailDescDeliveryTime?.text = productDescDeliveryTime

            }
        }
    }
}