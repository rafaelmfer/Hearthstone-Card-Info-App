package com.rafaelmfer.hearthstonecardinfoapp.di

import com.rafaelmfer.hearthstonecardinfoapp.data.repository.HearthstoneRepository
import com.rafaelmfer.hearthstonecardinfoapp.domain.repository.IHearthstoneRepository
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        factory<IHearthstoneRepository> { HearthstoneRepository(get()) }
    }
}