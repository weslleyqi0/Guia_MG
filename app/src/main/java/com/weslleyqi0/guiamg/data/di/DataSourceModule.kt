package com.weslleyqi0.guiamg.data.di

import com.weslleyqi0.guiamg.data.CustomerDataSource
import com.weslleyqi0.guiamg.data.FirebaseCustomerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindCustomerDataSource(dataSource: FirebaseCustomerDataSource) : CustomerDataSource
}