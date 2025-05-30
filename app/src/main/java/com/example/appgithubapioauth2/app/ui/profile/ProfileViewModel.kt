package com.example.appgithubapioauth2.app.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgithubapioauth2.app.data.model.GithubProfile
import com.example.appgithubapioauth2.app.data.repository.GithubRepository
import com.example.appgithubapioauth2.auth.data.plugin.ResourcesPlugin
import com.example.appgithubapioauth2.auth.data.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val githubRepository: GithubRepository,
    private val tokenRepository: TokenRepository

) : ViewModel() {

    private val _userProfileLiveData = MutableLiveData<GithubProfile?>()
    val userProfileLiveData: LiveData<GithubProfile?> get() = _userProfileLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = tokenRepository.getAccessToken() ?: return@launch
            val result = githubRepository.getAuthenticatedUser(token)
            result.onSuccess {
                _userProfileLiveData.postValue(it)
            }.onFailure {
                // handle error
            }
        }
    }
}