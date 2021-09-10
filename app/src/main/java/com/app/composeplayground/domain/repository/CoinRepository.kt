package com.app.composeplayground.domain.repository

import com.app.composeplayground.data.remote.dto.CoinDetailDto
import com.app.composeplayground.data.remote.dto.CoinDto
//Define repositories functions
interface CoinRepository {
    suspend fun getCoins():List<CoinDto>
    suspend fun getCoinById(coinId:String):CoinDetailDto
}