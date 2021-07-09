package com.shubhamkumarwinner.multipleviewrecyclerview.di

import com.shubhamkumarwinner.multipleviewrecyclerview.data.network.Api
import com.shubhamkumarwinner.multipleviewrecyclerview.data.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTargetApi(
        remoteDataSource: RemoteDataSource
    ): Api {
        return remoteDataSource.buildApi(Api::class.java)
    }
}