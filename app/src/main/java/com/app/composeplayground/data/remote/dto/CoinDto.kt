package com.app.composeplayground.data.remote.dto

import com.app.composeplayground.domain.model.Coin
import com.google.gson.annotations.SerializedName
//Gets data from api
data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

//Map data from api to domain model
fun CoinDto.toCoin():Coin{
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}