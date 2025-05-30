package com.example.appgithubapioauth2.app.di

import com.example.appgithubapioauth2.app.data.network.GithubApiService
import com.example.appgithubapioauth2.app.data.repository.GithubRepository
import com.example.appgithubapioauth2.app.ui.createrepositories.CreateRepoViewModel
import com.example.appgithubapioauth2.app.ui.followers.FollowersViewModel
import com.example.appgithubapioauth2.app.ui.profile.ProfileViewModel
import com.example.appgithubapioauth2.app.ui.repositories.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory


val appModules = module {

    factory { GithubRepository(get()) }

    viewModel { FollowersViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { RepoViewModel(get(), get()) }
    viewModel { CreateRepoViewModel(get(), get()) }

}

val networkAppModule = module {
    single {
        retrofit2.Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(GithubApiService::class.java)
    }
}