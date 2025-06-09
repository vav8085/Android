package com.vav.data.stockList.repository

import com.vav.data.stockList.datasource.StockListRemoteDataSource
import com.vav.data.stockList.mapper.toDomain
import com.vav.domain.stockList.model.StockListItem
import com.vav.domain.stockList.repository.StockListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StockListRepoImpl @Inject constructor(
    private val stockListRemoteDataSource: StockListRemoteDataSource
) : StockListRepository {
    override fun getStockList(): Flow<Result<List<StockListItem>>> {
        return stockListRemoteDataSource.getStockData().map { stockItemDtoList ->
            Result.success(stockItemDtoList.toDomain())
        }.flowOn(Dispatchers.IO).catch { exception ->
            emit(Result.failure(exception))
        }
    }
}