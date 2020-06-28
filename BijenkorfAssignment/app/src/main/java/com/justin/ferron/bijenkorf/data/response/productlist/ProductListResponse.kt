package com.justin.ferron.bijenkorf.data.response.productlist

import com.justin.ferron.bijenkorf.data.response.productlist.Data


data class ProductListResponse(
    val apiVersion: String,
    val `data`: Data,
    val message: String
)