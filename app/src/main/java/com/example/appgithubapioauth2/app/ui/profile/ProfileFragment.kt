package com.example.appgithubapioauth2.app.ui.profile



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.example.appgithubapioauth2.R
import com.example.appgithubapioauth2.databinding.FragmentProfileBinding
import com.example.appgithubapioauth2.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUserProfile()
        observeError()
        viewModel.getUserProfile()
        setupCloseButton()
    }

    private fun setupCloseButton() {
        binding.buttonClose.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }

    private fun observeError() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                toast(it)
            }
        }
    }

    private fun observeUserProfile() {
        viewModel.userProfileLiveData.observe(viewLifecycleOwner) { profile ->
            profile?.let {
                imageProfile(it.avatarUrl)
                binding.profileTextView.text = it.name
            }
        }
    }

    private fun imageProfile(imageUrl: String?) {
        imageUrl?.let {
            binding.profileImageView.load(it) {
                transformations(CircleCropTransformation())
                placeholder(R.drawable.github_logo)
                error(R.drawable.github_logo)
            }
        }
    }
}
