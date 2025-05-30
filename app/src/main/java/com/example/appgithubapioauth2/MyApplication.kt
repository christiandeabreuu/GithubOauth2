package com.example.appgithubapioauth2

import android.app.Application
import com.example.appgithubapioauth2.app.di.appModules
import com.example.appgithubapioauth2.app.di.networkAppModule
import com.example.appgithubapioauth2.auth.di.authModules
import com.example.appgithubapioauth2.auth.di.networkAuthModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                authModules,
                networkAuthModule,
                appModules,
                networkAppModule,
//                databaseModule
            )
        }
    }
}