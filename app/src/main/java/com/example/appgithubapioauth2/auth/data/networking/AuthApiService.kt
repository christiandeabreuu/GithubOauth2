package com.example.appgithubapioauth2.auth.data.networking

import com.example.appgithubapioauth2.auth.data.model.GithubTokens
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String
    ): Response<GithubTokens>
}