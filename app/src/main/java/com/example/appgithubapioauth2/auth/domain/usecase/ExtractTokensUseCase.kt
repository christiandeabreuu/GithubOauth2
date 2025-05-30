package com.example.appgithubapioauth2.auth.domain.usecase

import android.net.Uri
import com.example.appgithubapioauth2.auth.data.model.GithubTokens
//
//    class ExtractTokensUseCase {
//        fun execute(uri: Uri): GithubTokens {
//            val accessToken = uri.getQueryParameter(ACCESS_TOKEN_KEY).orEmpty()
//            val refreshToken = uri.getQueryParameter(REFRESH_TOKEN_KEY).orEmpty()
//            val authorizationCode = uri.getQueryParameter("code")
//            return GithubTokens(accessToken, refreshToken, authorizationCode.orEmpty())
//        }
//        private companion object
//        {
//             const val ACCESS_TOKEN_KEY = "access_token"
//             const val REFRESH_TOKEN_KEY = "refresh_token"
//        }
//
//    }