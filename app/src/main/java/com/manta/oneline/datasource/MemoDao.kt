package com.manta.oneline.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.manta.oneline.data.Memo
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface MemoDao {
    @Query("SELECT * FROM Memo ORDER BY RANDOM() LIMIT 100")
    fun getRandomMemo() : Maybe<List<Memo>>

    @Insert
    fun createMemo(memo : Memo) : Completable
}