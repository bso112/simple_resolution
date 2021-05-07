package com.manta.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manta.data.Entity.MemoEntity
import com.manta.data.datasource.MemoDatasource

@Database(entities = arrayOf(MemoEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDatasource() : MemoDatasource

}