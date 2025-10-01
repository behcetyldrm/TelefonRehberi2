package com.example.telefonrehberi2.module

import android.content.Context
import androidx.room.Room
import com.example.telefonrehberi2.database.RehberDao
import com.example.telefonrehberi2.database.RehberDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : RehberDatabase {
        return Room.databaseBuilder(
            context,
            RehberDatabase::class.java,
            "rehber_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: RehberDatabase) : RehberDao {
        return db.rehberDao()
    }
}