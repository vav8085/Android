package com.vav.data.mapper

import com.vav.data.model.StockItemDto
import com.vav.domain.model.StockItem
import java.util.UUID

fun StockItemDto.toDomain(): StockItem {
    return StockItem(
        id = UUID.randomUUID().toString(),
        symbol = this.symbol,
        companyName = this.companyName,
        currentPrice = this.currentPrice,
        priceChange = this.priceChange,
        priceChangePercent = this.priceChangePercent,
        chartIconUrl = this.chartIconUrl,
    )
}

fun List<StockItemDto>.toDomain() = this.map { it.toDomain() }