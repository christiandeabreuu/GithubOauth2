package com.example.appgithubapioauth2.app.data.model

import com.google.gson.annotations.SerializedName

data class GithubFollowers(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val htmlUrl: String
    // Adicione outros campos se precisar, esses são os principais para a tela de followers
)

data class GithubProfile(
    @SerializedName("login") val login: String,
    @SerializedName("name") val name: String?,
    @SerializedName("avatar_url") val avatarUrl: String
)

data class GithubUser(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("name") val name: String?, // pode ser null
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val htmlUrl: String
    // outros campos se quiser
)

data class GithubRepo(
    val id: Long,
    val name: String,
    val description: String?,
    val language: String?,
    val stargazers_count: Int,
    val forks_count: Int,
    val html_url: String
    // adicione mais campos se quiser!
)

data class CreateRepoRequest(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("private") val privateRepo: Boolean = false
)