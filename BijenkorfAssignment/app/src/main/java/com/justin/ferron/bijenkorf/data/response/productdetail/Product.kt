package com.justin.ferron.bijenkorf.data.response.productdetail

data class Product(
    val brand: Brand,
    val bundle: Any,
    val bundleAsVariant: Boolean,
    val code: String,
    val colorCount: Int,
    val crossSell: Any,
    val currentVariantProduct: CurrentVariantProduct,
    val defaultColor: Any,
    val defaultRelatedProducts: Any,
    val defaultVariantCode: String,
    val description: Any,
    val designer: Boolean,
    val detailPageVariation: Boolean,
    val displayName: String,
    val fromPricePresent: Boolean,
    val gift: Boolean,
    val marketingImageUrls: Any,
    val modelVersion: String,
    val name: String,
    val priceAsVariant: Boolean,
    val promotionName: Any,
    val recommendationRanking: Int,
    val returnInstructions: Any,
    val shipping: Shipping,
    val showQuantity: Boolean,
    val similarItems: List<SimilarItem>,
    val sizeAsVariant: Boolean,
    val trackingPath: String,
    val url: String
)