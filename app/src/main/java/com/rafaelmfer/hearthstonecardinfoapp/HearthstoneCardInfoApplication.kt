package com.rafaelmfer.hearthstonecardinfoapp

import android.app.Application
import com.rafaelmfer.hearthstonecardinfoapp.di.NetworkModule
import com.rafaelmfer.hearthstonecardinfoapp.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HearthstoneCardInfoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HearthstoneCardInfoApplication)
            modules(
                RepositoryModule.module,
                NetworkModule.module,
            )
        }
    }
}