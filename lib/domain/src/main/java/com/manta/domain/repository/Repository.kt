package com.manta.domain.repository


import androidx.lifecycle.LiveData
import com.manta.domain.data.MemoData
import com.manta.domain.data.UserData
import io.reactivex.Completable


interface Repository{
    fun getAll() : LiveData<List<MemoData>>
    fun createMemo(memo : MemoData) : Completable
    fun deleteAllMemo() : Completable
    fun updateMemo(memo : MemoData) : Completable
    fun deleteMemo(memo : MemoData) : Completable
    fun getRandomMemo() : LiveData<MemoData>
    fun getUserData() : UserData

}