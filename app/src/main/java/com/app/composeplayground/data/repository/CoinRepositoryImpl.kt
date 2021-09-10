package com.app.composeplayground.data.repository

import com.app.composeplayground.data.remote.CoinPaprikaApi
import com.app.composeplayground.data.remote.dto.CoinDetailDto
import com.app.composeplayground.data.remote.dto.CoinDto
import com.app.composeplayground.domain.repository.CoinRepository
import javax.inject.Inject
//Repository implementation
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}