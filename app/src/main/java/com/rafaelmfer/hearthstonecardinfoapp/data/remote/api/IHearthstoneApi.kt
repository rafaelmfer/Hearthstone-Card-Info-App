package com.rafaelmfer.hearthstonecardinfoapp.data.remote.api

import com.rafaelmfer.hearthstonecardinfoapp.data.remote.response.CardsInfoResponse
import retrofit2.http.GET

interface IHearthstoneApi {

    @GET("info")
    suspend fun getCardsInfo(): CardsInfoResponse
}