package com.manta.data.Entity

import androidx.annotation.ColorRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntity (
    @ColumnInfo(name = "content") val content : String  = "",
    @ColumnInfo(name = "date") val date : String  = "",
    @PrimaryKey(autoGenerate = true) val uid : Int = 0
    ){
}