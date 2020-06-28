package com.justin.ferron.bijenkorf.data.response.productlist


data class CurrentVariantProduct(
    val bundleSet: Boolean,
    val code: String,
    val color: String,
    val current: Boolean,
    val deliveryTime: Any,
    val department: Any,
    val departmentName: Any,
    val ean: Any,
    val images: List<Image>,
    val marketingImageUrls: List<String>,
    val minOrderQuantity: Any,
    val modelAlsoWears: Any,
    val relatedProducts: Any,
    val retailSet: Boolean,
    val selectionImage: Any,
    val sellingPrice: SellingPrice,
    val size: String,
    val url: String
)