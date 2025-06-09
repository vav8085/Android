package com.vav.data.stockList.mapper

import com.vav.data.stockList.model.StockListItemDto
import com.vav.domain.stockList.model.StockListItem
import java.util.UUID

fun StockListItemDto.toDomain(): StockListItem {
    return StockListItem(
        id = UUID.randomUUID().toString(),
        symbol = this.symbol,
        companyName = this.companyName,
        currentPrice = this.currentPrice,
        priceChange = this.priceChange,
        priceChangePercent = this.priceChangePercent,
        chartIconUrl = this.chartIconUrl,
    )
}

fun List<StockListItemDto>.toDomain() = this.map { it.toDomain() }