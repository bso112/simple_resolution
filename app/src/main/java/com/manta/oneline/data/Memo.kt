package com.manta.oneline.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Memo (
   var content : String,
   var date : String,
   @PrimaryKey(autoGenerate = true) val uid : Long = 0
)