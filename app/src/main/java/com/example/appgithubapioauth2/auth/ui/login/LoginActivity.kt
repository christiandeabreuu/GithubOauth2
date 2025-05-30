package com.example.appgithubapioauth2.auth.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appgithubapioauth2.R
import com.example.appgithubapioauth2.app.ui.AppActivity
import com.example.appgithubapioauth2.databinding.ActivityLoginBinding
import com.example.appgithubapioauth2.utils.NetworkUtils
import com.example.appgithubapioauth2.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.data?.let { loginViewModel.processRedirect(it) }

        setupButtonListeners()
//        handleRedirect(intent)
        observeNavigation()
        observeAuthResult()
    }

    private fun observeAuthResult() {
        loginViewModel.authResult.observe(this) { result ->
            result?.onSuccess { githubTokens ->
                toast("sucesso")
                githubTokens.accessToken?.let { loginViewModel.updateAccessToken(it) }
            }?.onFailure {
                toast(getString(R.string.auth_error))
            }
        }
    }

    private fun observeNavigation() {
        loginViewModel.navigateToArtists.observe(this) {
            val intent = Intent(this, AppActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { loginViewModel.processRedirect(it) }
    }


    private fun setupButtonListeners() {
        binding.buttonStart.setOnClickListener {
            if (NetworkUtils.isInternetAvailable(this)) {
                startActivity(loginViewModel.getAuthIntent())
            } else {
                navigateToOfflineMode()
            }
        }
    }

    private fun navigateToOfflineMode() {
        val intent = Intent(this, AppActivity::class.java)
        toast(getString(R.string.offline_mode))
        startActivity(intent)
    }

//    private fun handleRedirect(intent: Intent?) {
//        intent?.data?.let { uri ->
//            if (uri.toString().startsWith(Constants.REDIRECT_URI)) {
//                loginViewModel.handleRedirect(uri, Constants.REDIRECT_URI).observe(this) { result ->
//                    result?.onSuccess {
//                        navigateToTopAppActivity()
//                    }
//                }
//            }
//        }
//    }

    private fun navigateToTopAppActivity() {
        val intent = Intent(this, AppActivity::class.java)
        startActivity(intent)
        finish()
    }
}