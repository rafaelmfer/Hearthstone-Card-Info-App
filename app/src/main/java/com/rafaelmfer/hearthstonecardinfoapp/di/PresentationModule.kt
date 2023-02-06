package com.rafaelmfer.hearthstonecardinfoapp.di

import com.rafaelmfer.hearthstonecardinfoapp.presentation.MainViewModel
import com.rafaelmfer.hearthstonecardinfoapp.presentation.carddetails.CardDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModel { MainViewModel(get()) }
        viewModel { (cardId: String) -> CardDetailsViewModel(cardId, get()) }
    }
}