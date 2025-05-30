package com.example.appgithubapioauth2.auth.domain.usecase

import com.example.appgithubapioauth2.auth.data.model.GithubTokens
import com.example.appgithubapioauth2.auth.data.repository.AuthRepository

class GetAccessTokenUseCase(
    private val repository: AuthRepository
) {
    suspend fun execute(authorizationCode: String, redirectUri: String): Result<GithubTokens> {
        return repository.getAccessToken(authorizationCode, redirectUri)
    }
}


