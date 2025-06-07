package com.vav.data.model

import com.vav.domain.model.StockItem

data class StockItemDto(
    val symbol: String,
    val companyName: String,
    val currentPrice: Double,
    val priceChange: Double,
    val priceChangePercent: Double,
    val chartIconUrl: String? = null
)
