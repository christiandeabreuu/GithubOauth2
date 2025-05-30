package com.example.appgithubapioauth2.auth.data.repository

import android.util.Log
import com.example.appgithubapioauth2.auth.data.model.GithubTokens
import com.example.appgithubapioauth2.auth.data.networking.AuthApiService
import com.example.appgithubapioauth2.auth.data.plugin.ResourcesPlugin

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val resourcesPlugin: ResourcesPlugin
) : AuthRepository {

    override suspend fun getAccessToken(
        authorizationCode: String,
        redirectUri: String
    ): Result<GithubTokens> {
        return try {
            val response = apiService.getAccessToken(
                clientId = "Ov23liIPmGK4hmLO7XYn",
                clientSecret = "aadf56bc9d504ed04bc90bdb39c7d08215f82a7b",
                redirectUri = redirectUri,
                code = authorizationCode
            )

            val body = response.body()
            Log.d("GITHUB_AUTH", "Response body: $body")

            if (!response.isSuccessful || body == null) {
                Log.e("GITHUB_AUTH", "Request failed: ${response.code()} ${response.message()}")
                return Result.failure(
                    Exception(
                        resourcesPlugin.getRequestTokenErrorMessage(
                            response.code().toString(), response.message()
                        )
                    )
                )
            }

            if (body.accessToken.isNullOrEmpty()) {
                Log.e(
                    "GITHUB_AUTH",
                    "Access token is null! Error: ${body.error} ${body.errorDescription}"
                )
                return Result.failure(
                    Exception(
                        "Access token is null! Error: ${body.error} ${body.errorDescription}"
                    )
                )
            }

            Result.success(body)
        } catch (e: Exception) {
            Log.e("GITHUB_AUTH", "Exception during token request", e)
            Result.failure(e)
        }
    }
}