package com.example.appgithubapioauth2.auth.data.repository

interface TokenRepository {
    fun saveTokens(accessToken: String): Boolean
    fun getAccessToken(): String?
}