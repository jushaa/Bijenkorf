package com.justin.ferron.bijenkorf.data.response.productdetail

data class Availability(
    val available: Boolean,
    val availableFuture: Boolean,
    val canBeOrdered: Boolean,
    val orderableNow: Boolean,
    val stock: Int
)