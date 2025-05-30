package com.example.appgithubapioauth2.app.ui.createrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgithubapioauth2.app.data.model.CreateRepoRequest
import com.example.appgithubapioauth2.app.data.model.GithubRepo
import com.example.appgithubapioauth2.app.data.repository.GithubRepository
import com.example.appgithubapioauth2.auth.data.repository.TokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateRepoViewModel(
    private val repository: GithubRepository, private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _repoCreatedLiveData = MutableLiveData<GithubRepo?>()
    val repoCreatedLiveData: LiveData<GithubRepo?> get() = _repoCreatedLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun createRepository(name: String, description: String?, privateRepo: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = tokenRepository.getAccessToken() ?: return@launch
            val request = CreateRepoRequest(name, description, privateRepo)
            val result = repository.createRepository(token, request)
            result.onSuccess { repo ->
                _repoCreatedLiveData.postValue(repo)
            }.onFailure {
                _errorLiveData.postValue("Erro ao criar repositório")
            }
        }
    }
}