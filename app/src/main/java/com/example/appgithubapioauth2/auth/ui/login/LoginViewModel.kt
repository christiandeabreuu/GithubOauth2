package com.example.appgithubapioauth2.auth.ui.login

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.appgithubapioauth2.auth.data.model.GithubTokens
import com.example.appgithubapioauth2.auth.data.plugin.ResourcesPlugin
import com.example.appgithubapioauth2.auth.domain.usecase.AuthUseCase
import com.example.appgithubapioauth2.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUseCase: AuthUseCase,
    private val resourcesPlugin: ResourcesPlugin
) : ViewModel() {

    private val _connectionStatus = MutableLiveData<Boolean>()
    val connectionStatus: LiveData<Boolean> get() = _connectionStatus

    private val _navigateToArtists = MutableLiveData<String>()
    val navigateToArtists: LiveData<String> get() = _navigateToArtists

    private val _authResult = MutableLiveData<Result<GithubTokens>>()
    val authResult: LiveData<Result<GithubTokens>> get() = _authResult

    private val _authError = MutableLiveData<String>()
    val authError: LiveData<String> get() = _authError

    fun updateAccessToken(accessToken: String) {
        _navigateToArtists.postValue(accessToken)
    }

    fun processRedirect(uri: Uri) {
        val code = uri.getQueryParameter(Constants.CODE)
        if (!code.isNullOrEmpty()) {
            viewModelScope.launch {
                val result = authUseCase.authenticate(code, Constants.REDIRECT_URI)
                _authResult.postValue(result)
                result.onSuccess { it.accessToken?.let { it1 -> updateAccessToken(it1) } }
            }
        } else {
            _authError.postValue(resourcesPlugin.handleDirectCodeNotFoundMessage())
        }
    }

    fun getAuthIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/login/oauth/authorize?client_id=Ov23liIPmGK4hmLO7XYn&redirect_uri=myapp%3A%2F%2Fcallback&scope=repo+user&skip_account_picker=true")
        )
    }

//    fun handleRedirect(uri: Uri, redirectUri: String): LiveData<Result<GithubTokens>> =
//        liveData(Dispatchers.IO) {
//            val authorizationCode = uri.getQueryParameter(Constants.CODE) ?: run {
//                _authError.postValue(resourcesPlugin.handleDirectCodeNotFoundMessage())
//                return@liveData
//            }
//
//            val result = authUseCase.authenticate(authorizationCode, redirectUri)
//            result.onSuccess {
//                _authResult.postValue(Result.success(it))
//                updateAccessToken(it.accessToken)
//            }.onFailure {
//                _authError.postValue(it.message)
//            }
//        }
}
