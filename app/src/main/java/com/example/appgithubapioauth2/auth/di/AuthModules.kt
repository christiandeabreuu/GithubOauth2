package com.example.appgithubapioauth2.auth.di

import com.example.appgithubapioauth2.auth.data.networking.AuthApiService
import com.example.appgithubapioauth2.auth.data.plugin.ResourcesPlugin
import com.example.appgithubapioauth2.auth.data.plugin.ResourcesPluginImpl
import com.example.appgithubapioauth2.auth.data.repository.AuthRepository
import com.example.appgithubapioauth2.auth.data.repository.AuthRepositoryImpl
import com.example.appgithubapioauth2.auth.data.repository.TokenRepository
import com.example.appgithubapioauth2.auth.data.repository.TokenRepositoryImpl
import com.example.appgithubapioauth2.auth.domain.usecase.AuthUseCase
import com.example.appgithubapioauth2.auth.domain.usecase.GetAccessTokenUseCase
import com.example.appgithubapioauth2.auth.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val authModules = module {
    factory<AuthRepository> { AuthRepositoryImpl(apiService = get(), resourcesPlugin = get()) }
    factory<TokenRepository> { TokenRepositoryImpl(context = get()) }

    viewModel { LoginViewModel(get(), get()) }

    factory { GetAccessTokenUseCase(get()) }
    factory { AuthUseCase(get(), get(), get()) }

    factory<ResourcesPlugin> { ResourcesPluginImpl(get()) }

}

val networkAuthModule = module {

    single {
        retrofit2.Retrofit.Builder().baseUrl("https://github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(AuthApiService::class.java)
    }
}




