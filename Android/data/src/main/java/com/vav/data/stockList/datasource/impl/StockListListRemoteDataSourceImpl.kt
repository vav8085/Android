package com.vav.data.stockList.datasource.impl

import com.vav.data.stockList.datasource.StockListRemoteDataSource
import com.vav.data.stockList.model.StockListItemDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class StockListListRemoteDataSourceImpl : StockListRemoteDataSource {
    override fun getStockData(): Flow<List<StockListItemDto>> {
        return flow {
            delay(1000.milliseconds)

            val data = generateStockData()

            if (Random.nextInt(0,10) < 4) {
                throw Exception("Network Error!")
            }
            emit(data)
        }
    }

    fun generateStockData(): List<StockListItemDto> {
        val symbolsToName = listOf<Pair<String, String>>(
            "APPL" to "Apple",
            "GOOGL" to "Google",
            "TSLA" to "Tesla",
            "Meta" to "Meta",
            "MSFT" to "Microsoft Corp",
            "AMZN" to "Amazon",
            "JPMC" to "JPMorgan Chase",
            "BOFA" to "Bank Of America",
            "AMEX" to "American Express",
            "KO" to "The Coca Cola Company",
        )
        return symbolsToName.map { (symbol, name) ->
            val price = Random.nextDouble(100.00, 500.00)
            val change = Random.nextDouble(-0.5, 0.5)
            val changePercent = change / price

            StockListItemDto(
                symbol = symbol,
                companyName = name,
                currentPrice = price,
                priceChange = change,
                priceChangePercent = changePercent,
                chartIconUrl = null
            )
        }.shuffled()
    }
}