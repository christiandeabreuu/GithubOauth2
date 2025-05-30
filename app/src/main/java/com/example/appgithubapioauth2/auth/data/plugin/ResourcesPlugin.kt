package com.example.appgithubapioauth2.auth.data.plugin

interface ResourcesPlugin {

    fun getRequestTokenErrorMessage(code: String, message: String): String
    fun getAccessTokenErrorMessage(): String
    fun getSaveTokenErrorMessage(): String
    fun handleDirectCodeNotFoundMessage(): String
    fun createPlaylistErrorMessage(): String
    fun playlistCreatedSuccessMessage(playlistName: String): String
    fun validatePlaylistNameMessage(): String
    fun searchPlaylistsErrorMessage(): String
    fun searchProfileErrorMessage(): String
}
