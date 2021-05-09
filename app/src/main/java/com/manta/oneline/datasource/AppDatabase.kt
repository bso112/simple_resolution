package com.manta.oneline.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manta.oneline.data.Memo

@Database(entities = arrayOf(Memo::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao() : MemoDao

}