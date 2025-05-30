package com.example.appgithubapioauth2.auth.data.plugin

import android.content.Context
import com.example.appgithubapioauth2.R

class ResourcesPluginImpl(private val context: Context) : ResourcesPlugin {

    override fun getRequestTokenErrorMessage(code: String, message: String): String =
        context.getString(R.string.error_message, code, message)

    override fun getAccessTokenErrorMessage(): String =
        context.getString(R.string.error_message_get_token)

    override fun getSaveTokenErrorMessage(): String =
        context.getString(R.string.error_message_save_token)

    override fun handleDirectCodeNotFoundMessage(): String =
        context.getString(R.string.error_message_direct_code_not_found)

    override fun createPlaylistErrorMessage(): String =
        context.getString(R.string.error_create_playlist)

    override fun playlistCreatedSuccessMessage(playlistName: String): String =
        context.getString(R.string.playlist_created_success_with_name, playlistName)

    override fun searchPlaylistsErrorMessage(): String =
        context.getString(R.string.error_message_search_playlists)

    override fun searchProfileErrorMessage(): String =
        context.getString(R.string.error_message_search_profile)

    override fun validatePlaylistNameMessage(): String =
        context.getString(R.string.insert_name_your_repo_title)
}
