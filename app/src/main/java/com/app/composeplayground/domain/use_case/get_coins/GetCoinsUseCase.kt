package com.app.composeplayground.domain.use_case.get_coins

import com.app.composeplayground.common.Resource
import com.app.composeplayground.data.remote.dto.toCoin
import com.app.composeplayground.domain.model.Coin
import com.app.composeplayground.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//Read data from the repository
class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))

        }catch (e:HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage?:"An unexpected error occured"))
        }catch (ex:IOException){
            emit(Resource.Error<List<Coin>>("Couldn't reach server check you internet connection"))
        }
    }
}