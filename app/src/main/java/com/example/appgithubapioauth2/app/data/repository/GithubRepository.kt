package com.example.appgithubapioauth2.app.data.repository

import com.example.appgithubapioauth2.app.data.model.CreateRepoRequest
import com.example.appgithubapioauth2.app.data.model.GithubFollowers
import com.example.appgithubapioauth2.app.data.model.GithubProfile
import com.example.appgithubapioauth2.app.data.model.GithubRepo
import com.example.appgithubapioauth2.app.data.model.GithubUser
import com.example.appgithubapioauth2.app.data.network.GithubApiService

class GithubRepository(private val apiService: GithubApiService) {

    suspend fun getFollowers(token: String): Result<List<GithubFollowers>> {
        return try {
            val followers = apiService.getFollowers("Bearer $token")
            Result.success(followers)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }suspend fun getAuthenticatedUser(token: String): Result<GithubProfile> {
        return try {
            val user = apiService.getAuthenticatedUser("Bearer $token")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserDetails(username: String, token: String): Result<GithubUser> {
        return try {
            val user = apiService.getUserDetails(username, "Bearer $token")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAuthenticatedUserRepos(token: String): Result<List<GithubRepo>> {
        return try {
            val repos = apiService.getAuthenticatedUserRepos("Bearer $token")
            Result.success(repos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createRepository(token: String, request: CreateRepoRequest): Result<GithubRepo> {
        return try {
            val repo = apiService.createRepository("Bearer $token", createRepoRequest = request)
            Result.success(repo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}