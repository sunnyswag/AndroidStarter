package com.example.roomstarter.room.module

import com.example.roomstarter.room.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) =
        appDatabase.userDao()
}