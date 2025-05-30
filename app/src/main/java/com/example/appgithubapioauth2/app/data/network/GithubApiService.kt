package com.example.appgithubapioauth2.app.data.network

import com.example.appgithubapioauth2.app.data.model.CreateRepoRequest
import com.example.appgithubapioauth2.app.data.model.GithubFollowers
import com.example.appgithubapioauth2.app.data.model.GithubProfile
import com.example.appgithubapioauth2.app.data.model.GithubRepo
import com.example.appgithubapioauth2.app.data.model.GithubUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("user/followers")
    suspend fun getFollowers(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): List<GithubFollowers>

    @GET("user")
    suspend fun getAuthenticatedUser(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "application/vnd.github+json"
    ): GithubProfile

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String,
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "application/vnd.github+json"
    ): GithubUser

    @GET("user/repos")
    suspend fun getAuthenticatedUserRepos(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): List<GithubRepo>

    @POST("user/repos")
    suspend fun createRepository(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Body createRepoRequest: CreateRepoRequest
    ): GithubRepo

    // Se quiser, já deixa pronto para pegar "quem eu sigo":
    @GET("user/following")
    suspend fun getFollowing(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): GithubFollowers
}