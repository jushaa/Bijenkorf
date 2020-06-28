package com.justin.ferron.bijenkorf.data.response.productdetail

data class SimilarItem(
    val id: String,
    val name: String,
    val noFollow: Boolean,
    val query: String,
    val relativeUrl: String,
    val url: String
)