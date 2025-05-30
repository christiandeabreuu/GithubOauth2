package com.example.appgithubapioauth2.auth.domain.usecase

import com.example.appgithubapioauth2.auth.data.model.GithubTokens
import com.example.appgithubapioauth2.auth.data.plugin.ResourcesPlugin
import com.example.appgithubapioauth2.auth.data.repository.TokenRepository

class AuthUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val tokenRepository: TokenRepository,
    private val resourcesPlugin: ResourcesPlugin
) {
    suspend fun authenticate(
        authorizationCode: String,
        redirectUri: String
    ): Result<GithubTokens> {
        val tokensResult = getAccessTokenUseCase.execute(authorizationCode, redirectUri)
        val tokens = tokensResult.getOrNull() ?: return Result.failure(Exception(resourcesPlugin.getAccessTokenErrorMessage()))

        if (tokens.accessToken.isNullOrEmpty()) {
            return Result.failure(Exception("Access token is null or empty!"))
        }

        return if (tokenRepository.saveTokens(tokens.accessToken)) {
            Result.success(tokens)
        } else {
            Result.failure(Exception(resourcesPlugin.getSaveTokenErrorMessage()))
        }
    }
}