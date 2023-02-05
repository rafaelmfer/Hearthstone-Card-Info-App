package com.rafaelmfer.hearthstonecardinfoapp.domain.repository

import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel

interface IHearthstoneRepository {
    suspend fun getCardsInfo(): State<CardsInfoModel>
    suspend fun getCardsByClass(playerClass: String): State<List<CardModel>>
}