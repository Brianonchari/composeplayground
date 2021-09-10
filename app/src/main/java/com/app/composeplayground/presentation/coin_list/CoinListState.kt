package com.app.composeplayground.presentation.coin_list

import com.app.composeplayground.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
