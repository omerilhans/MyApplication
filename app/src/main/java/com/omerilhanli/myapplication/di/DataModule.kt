package com.omerilhanli.myapplication.di

import com.omerilhanli.myapplication.data.datasource.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataSource(): DataSource {
        return DataSource() //\\
    }
}