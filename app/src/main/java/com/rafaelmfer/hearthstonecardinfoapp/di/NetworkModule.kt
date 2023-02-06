package com.rafaelmfer.hearthstonecardinfoapp.di

import com.rafaelmfer.hearthstonecardinfoapp.data.network.HTTPClient
import com.rafaelmfer.hearthstonecardinfoapp.data.remote.api.IHearthstoneApi
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        single { HTTPClient() }
        factory { get<HTTPClient>().create(IHearthstoneApi::class) }
    }
}