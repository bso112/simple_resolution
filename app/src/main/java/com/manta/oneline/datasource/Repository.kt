package com.manta.oneline.datasource

import androidx.room.Room
import com.manta.oneline.App.Companion.context

object Repository {
    private val localDB: AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "database-name"
    ).build()


    val memoDao = localDB.memoDao()



}