package com.rafaelmfer.hearthstonecardinfoapp.data.remote.api

import com.rafaelmfer.hearthstonecardinfoapp.data.remote.response.CardResponse
import com.rafaelmfer.hearthstonecardinfoapp.data.remote.response.CardsInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IHearthstoneApi {

    @GET("info")
    suspend fun getCardsInfo(): CardsInfoResponse

    @GET("cards/classes/{playerClass}")
    suspend fun getCardsByClass(
        @Path("playerClass") playerClass: String
    ): List<CardResponse>

    @GET("cards/{cardId}")
    suspend fun getSingleCard(
        @Path("cardId") cardId: String
    ): List<CardResponse>
}