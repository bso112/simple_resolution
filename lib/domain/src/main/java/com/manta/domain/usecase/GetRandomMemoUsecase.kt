package com.manta.domain.usecase

import com.manta.domain.repository.Repository
import javax.inject.Inject

class GetRandomMemoUsecase @Inject constructor(private val repo : Repository){
    fun getRandomMemo() = repo.getRandomMemo()
}