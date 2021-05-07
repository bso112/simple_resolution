package com.manta.domain.repository


import androidx.lifecycle.LiveData
import com.manta.domain.data.MemoData
import com.manta.domain.data.UserData
import io.reactivex.Completable
import io.reactivex.Single


interface Repository{
    fun getAll() : LiveData<List<MemoData>>
    fun createMemo(memo : MemoData) : Completable
//    fun deleteAllMemo() : LiveData<Unit>
//    fun updateMemo(memo : MemoData) : LiveData<Unit>
//    fun deleteMemo(memo : MemoData) : LiveData<Unit>
    fun getRandomMemo() : LiveData<MemoData>
    fun getUserData() : UserData

}