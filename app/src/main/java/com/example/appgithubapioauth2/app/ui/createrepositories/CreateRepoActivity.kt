package com.example.appgithubapioauth2.app.ui.createrepositories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appgithubapioauth2.R
import com.example.appgithubapioauth2.databinding.ActivityCreateRepoBinding
import com.example.appgithubapioauth2.utils.NetworkUtils
import com.example.appgithubapioauth2.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateRepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRepoBinding
    private val viewModel: CreateRepoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeCreatePlaylist()
        observeError()
        setupCreateButton()
        setupCloseButton()
    }

    private fun observeError() {
        viewModel.errorLiveData.observe(this) { errorMessage ->
            errorMessage?.let {
                toast(it)
            }
        }
    }

    private fun observeCreatePlaylist() {
        viewModel.repoCreatedLiveData.observe(this) { repo ->
            if (repo != null) {
                toast(getString(R.string.playlist_created_success))
                finish()
            } else {
                toast(getString(R.string.error_create_playlist))
            }
        }
    }

    private fun setupCreateButton() {
        binding.createButton.setOnClickListener {
            val repoName = binding.repoNameEditText.text.toString().trim()
            if (repoName.isBlank()) {
                toast(getString(R.string.insert_name_your_repo_title))
                return@setOnClickListener
            }

            if (!NetworkUtils.isInternetAvailable(this)) {
                toast(getString(R.string.error_internet))
                return@setOnClickListener
            }
//            val name = binding.text.toString()
//            val description = binding.etRepoDescription.text.toString()
//            val isPrivate = binding.cbPrivate.isChecked
            viewModel.createRepository(repoName, null, false)
        }
    }

    private fun setupCloseButton() {
        binding.closeButton.setOnClickListener {
            finish()
        }
    }
}
