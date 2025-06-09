package com.vav.data.stockList.di

import com.vav.data.stockList.datasource.StockListRemoteDataSource
import com.vav.data.stockList.datasource.impl.StockListListRemoteDataSourceImpl
import com.vav.data.stockList.repository.StockListRepoImpl
import com.vav.domain.stockList.repository.StockListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StockListDataModule {
    @Binds
    @Singleton
    abstract fun bindStockRemoteDataSource(stockListRemoteDataSourceImpl: StockListListRemoteDataSourceImpl): StockListRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindStockListRepository(stockListRepoImpl: StockListRepoImpl): StockListRepository
}