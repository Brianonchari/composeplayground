package com.app.composeplayground.presentation.coin_detail

import com.app.composeplayground.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean = false,
    val coin:CoinDetail? = null,
    val error:String = ""
)
