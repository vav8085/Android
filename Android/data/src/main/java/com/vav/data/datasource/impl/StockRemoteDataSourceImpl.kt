package com.vav.data.datasource.impl

import com.vav.data.datasource.StockRemoteDataSource
import com.vav.data.model.StockItemDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class StockRemoteDataSourceImpl : StockRemoteDataSource {
    override fun getStockData(): Flow<List<StockItemDto>> {
        return flow {
            delay(1000.milliseconds)

            val data = generateStockData()

            if (Random.nextInt(0,10) < 4) {
                throw Exception("Network Error!")
            }
            emit(data)
        }
    }

    fun generateStockData(): List<StockItemDto> {
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

            StockItemDto(
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