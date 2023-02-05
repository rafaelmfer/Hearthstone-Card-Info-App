package com.rafaelmfer.hearthstonecardinfoapp.data.repository

import com.rafaelmfer.hearthstonecardinfoapp.data.remote.api.IHearthstoneApi
import com.rafaelmfer.hearthstonecardinfoapp.domain.mapper.asDomainModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.repository.IHearthstoneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * A sealed class that encapsulates successful outcome with a value of type [T]
 * or a failure with message and statusCode
 */
sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<out T>(val model: T) : State<T>()
    data class Error(val message: String?) : State<Nothing>()
}

class HearthstoneRepository(
    private val iHearthstoneApi: IHearthstoneApi,
) : IHearthstoneRepository {

    override suspend fun getCardsInfo(): State<CardsInfoModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = iHearthstoneApi.getCardsInfo()
                State.Success(response.asDomainModel())
            } catch (ex: IOException) {
                // Network Error
                State.Error(message = ex.localizedMessage)
            } catch (ex: Exception) {
                State.Error(message = ex.localizedMessage)
            }
        }
    }

    override suspend fun getCardsByClass(playerClass: String): State<List<CardModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = iHearthstoneApi.getCardsByClass(playerClass)
                State.Success(response.map { it.asDomainModel() })
            } catch (ex: IOException) {
                // Network Error
                State.Error(message = ex.localizedMessage)
            } catch (ex: Exception) {
                State.Error(message = ex.localizedMessage)
            }
        }
    }
}