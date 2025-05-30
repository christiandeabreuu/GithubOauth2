package com.example.appgithubapioauth2.auth.data.model

import com.google.gson.annotations.SerializedName


data class GithubTokens(
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("token_type") val tokenType: String?,
    @SerializedName("scope") val scope: String?,
    @SerializedName("error") val error: String? = null,
    @SerializedName("error_description") val errorDescription: String? = null
)

