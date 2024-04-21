package com.example.diploma_project.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductModel::class], version = 1)
abstract class AppDatabase :RoomDatabase(){


    companion object{
        private var database : AppDatabase? = null
        private val DATABASE_NAME = "DhakaCart_RoomDb"

        @Synchronized
        fun getInstance(conntext : Context): AppDatabase{
            if (database == null){

                database = Room.databaseBuilder(
                        conntext.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                        ).allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
            }
            return database!!
        }
    }

    abstract fun productDao() : ProductDao
}


