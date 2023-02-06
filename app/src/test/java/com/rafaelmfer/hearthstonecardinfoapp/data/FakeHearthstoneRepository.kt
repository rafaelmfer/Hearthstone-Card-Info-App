package com.rafaelmfer.hearthstonecardinfoapp.data

import com.rafaelmfer.hearthstonecardinfoapp.TestHelper
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.repository.IHearthstoneRepository
import kotlin.reflect.KClass

// Use FakeDataSource that acts as a test double to the LocalDataSource
class FakeHearthstoneRepository : IHearthstoneRepository {

    companion object {
        const val CARD_NOT_FOUND = "Card could not found"
        val cardInfoModel = readJson("card_info_model_mock.json", CardsInfoModel::class)
        val cardModel = readJson("card_model_mock.json", arrayOf<CardModel>())

        private fun <T : Any> readJson(fileName: String, clazz: KClass<T>): T {
            val jsonString = TestHelper.loadJsonAsString(fileName)
            return TestHelper.convertJsonToModel(jsonString, clazz.java)
        }

        private fun <T : Any> readJson(fileName: String, clazz: Array<T>): List<T> {
            val jsonString = TestHelper.loadJsonAsString(fileName)
            return TestHelper.convertJsonToListModel(jsonString, clazz)
        }
    }

    private var shouldReturnError = false

    fun shouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getCardsInfo(): State<CardsInfoModel> {
        if (shouldReturnError) {
            return State.Error(CARD_NOT_FOUND)
        }

        return State.Success(cardInfoModel)
    }

    override suspend fun getCardsByClass(playerClass: String): State<List<CardModel>> {
        if (shouldReturnError) {
            return State.Error(CARD_NOT_FOUND)
        }

        if (playerClass == "") {
            return State.Error(CARD_NOT_FOUND)
        }

        return State.Success(cardModel)
    }

    override suspend fun getSingleCard(cardId: String): State<List<CardModel>> {
        if (shouldReturnError) {
            return State.Error(CARD_NOT_FOUND)
        }

        return State.Success(cardModel.filter { it.cardId == cardId })
    }
}