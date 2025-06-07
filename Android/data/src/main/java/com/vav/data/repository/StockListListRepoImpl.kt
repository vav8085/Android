package com.vav.data.repository

import com.vav.data.datasource.StockRemoteDataSource
import com.vav.data.mapper.toDomain
import com.vav.domain.model.StockItem
import com.vav.domain.repository.StockListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StockListListRepoImpl @Inject constructor(
    private val stockRemoteDataSource: StockRemoteDataSource
) : StockListRepository {
    override fun getStockList(): Flow<Result<List<StockItem>>> {
        return stockRemoteDataSource.getStockData().map { stockItemDtoList ->
            Result.success(stockItemDtoList.toDomain())
        }.flowOn(Dispatchers.IO).catch { exception ->
            emit(Result.failure(exception))
        }
    }
}