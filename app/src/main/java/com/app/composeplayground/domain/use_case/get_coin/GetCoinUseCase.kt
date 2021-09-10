package com.app.composeplayground.domain.use_case.get_coin

import com.app.composeplayground.common.Resource
import com.app.composeplayground.data.remote.dto.toCoinDetail
import com.app.composeplayground.domain.model.CoinDetail
import com.app.composeplayground.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId:String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin= repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))

        }catch (e:HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?:"An unexpected error occured"))
        }catch (ex:IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server check you internet connection"))
        }
    }
}