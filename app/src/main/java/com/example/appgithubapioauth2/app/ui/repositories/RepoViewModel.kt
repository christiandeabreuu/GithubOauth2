package com.example.appgithubapioauth2.app.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgithubapioauth2.app.data.model.GithubRepo
import com.example.appgithubapioauth2.app.data.repository.GithubRepository
import com.example.appgithubapioauth2.auth.data.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoViewModel(
    private val githubRepository: GithubRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<GithubRepo>>()
    val reposLiveData: LiveData<List<GithubRepo>> get() = _reposLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getAuthenticatedUserRepos() {
        viewModelScope.launch(Dispatchers.IO) {
            val accessToken = tokenRepository.getAccessToken() ?: return@launch
            val result = githubRepository.getAuthenticatedUserRepos(accessToken)
            result.onSuccess { list ->
                _reposLiveData.postValue(list)
            }.onFailure {
                _errorLiveData.postValue("Erro ao buscar repositórios")
            }
        }
    }
}