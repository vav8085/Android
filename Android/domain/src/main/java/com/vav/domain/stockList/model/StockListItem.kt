package com.vav.domain.stockList.model

data class StockListItem(
    val id: String,
    val symbol: String,
    val companyName: String,
    val currentPrice: Double,
    val priceChange: Double,
    val priceChangePercent: Double,
    val chartIconUrl: String? = null
)