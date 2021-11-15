package com.anilderinbay.to_doapp.data.models.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anilderinbay.to_doapp.data.models.ToDoDatabase
import com.anilderinbay.to_doapp.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(
        context,ToDoDatabase::class.java,
        DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideDao(database : ToDoDatabase) = database.toDoDao()
}