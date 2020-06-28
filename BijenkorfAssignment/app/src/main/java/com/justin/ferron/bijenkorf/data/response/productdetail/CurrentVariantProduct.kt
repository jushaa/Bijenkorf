package com.justin.ferron.bijenkorf.data.response.productdetail

data class CurrentVariantProduct(
    val availability: Availability,
    val bundleSet: Boolean,
    val code: String,
    val color: String,
    val current: Boolean,
    val deliveryTime: String,
    val department: Any,
    val departmentName: Any,
    val displayProperties: DisplayProperties,
    val ean: Any,
    val images: List<Image>,
    val marketingImageUrls: List<String>,
    val minOrderQuantity: Int,
    val modelAlsoWears: ModelAlsoWears,
    val overriddenPrices: List<OverriddenPrice>,
    val relatedProducts: RelatedProducts,
    val retailSet: Boolean,
    val selectionImage: SelectionImage,
    val sellingPrice: SellingPrice,
    val size: String,
    val url: String
)