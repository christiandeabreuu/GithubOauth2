package com.example.appgithubapioauth2.auth.data.repository

import com.example.appgithubapioauth2.auth.data.model.GithubTokens

interface AuthRepository {

    suspend fun getAccessToken(
        authorizationCode: String, redirectUri: String
    ): Result<GithubTokens>
}
