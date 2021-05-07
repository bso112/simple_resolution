package com.manta.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.manta.data.Entity.MemoEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface MemoDatasource {
    @Query("SELECT * FROM memo")
    fun getAll() : LiveData<List<MemoEntity>>

    @Query("SELECT * FROM memo ORDER BY RANDOM() LIMIT 1")
    fun getRandomMemo() : LiveData<MemoEntity>

    @Insert
    fun createMemo(content : MemoEntity) : Completable

    @Query("DELETE FROM Memo")
    fun deleteAllMemo() : Completable

    @Update
    fun updateMemo(content : MemoEntity) : Completable

    @Delete
    fun deleteMemo(memo : MemoEntity) : Completable





}