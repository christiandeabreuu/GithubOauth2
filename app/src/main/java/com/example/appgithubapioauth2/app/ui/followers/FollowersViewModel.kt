package com.example.appgithubapioauth2.app.ui.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgithubapioauth2.app.data.model.GithubFollowers
import com.example.appgithubapioauth2.app.data.model.GithubProfile
import com.example.appgithubapioauth2.app.data.model.GithubUser
import com.example.appgithubapioauth2.app.data.repository.GithubRepository
import com.example.appgithubapioauth2.auth.data.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FollowersViewModel(
    private val githubRepository: GithubRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    private val _userProfileLiveData = MutableLiveData<GithubProfile>()
    val userProfileLiveData: LiveData<GithubProfile> get() = _userProfileLiveData

    private val _followersLiveData = MutableLiveData<List<GithubFollowers>>()
    val followersLiveData: LiveData<List<GithubFollowers>> get() = _followersLiveData

    private val _followerName = MutableLiveData<Map<String, String?>>()
    val followerName: LiveData<Map<String, String?>> get() = _followerName

    private val followerNamesMap = mutableMapOf<String, String?>()

    fun getFollowers() {
        viewModelScope.launch(Dispatchers.IO) {
            val accessToken = tokenRepository.getAccessToken() ?: return@launch
            val result = githubRepository.getFollowers(accessToken)
            result.onSuccess { list ->
                _followersLiveData.postValue(list)
            }.onFailure {
                _errorLiveData.postValue("Erro ao buscar followers")
            }
        }
    }

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

    private fun fetchFollowerName(login: String) {
        // Evita buscar 2x o mesmo login
        if (followerNamesMap.containsKey(login)) return

        viewModelScope.launch(Dispatchers.IO) {
            val token = tokenRepository.getAccessToken() ?: return@launch
            val result = githubRepository.getUserDetails(login, token)
            result.onSuccess { user: GithubUser ->
                followerNamesMap[login] = user.name
                _followerName.postValue(followerNamesMap.toMap())
            }
        }
    }

    fun fetchAllFollowerNames(followers: List<GithubFollowers>) {
        followers.forEach { fetchFollowerName(it.login) }
    }
}