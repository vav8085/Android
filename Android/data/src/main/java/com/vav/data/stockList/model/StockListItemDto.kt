package com.vav.data.stockList.model

data class StockListItemDto(
    val symbol: String,
    val companyName: String,
    val currentPrice: Double,
    val priceChange: Double,
    val priceChangePercent: Double,
    val chartIconUrl: String? = null
)
