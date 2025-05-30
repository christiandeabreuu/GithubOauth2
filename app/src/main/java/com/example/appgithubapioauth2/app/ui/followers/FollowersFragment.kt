package com.example.appgithubapioauth2.app.ui.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.appgithubapioauth2.databinding.FragmentFollowersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.appgithubapioauth2.utils.toast

class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private val viewModel: FollowersViewModel by viewModel()
    private lateinit var followersAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followersAdapter = FollowersAdapter()
        setupRecyclerView()
        setupObservers()

        viewModel.getFollowers()
        viewModel.getUserProfile()
    }

    private fun setupObservers() {
        viewModel.userProfileLiveData.observe(viewLifecycleOwner) { user ->
            binding.profileImageView.load(user.avatarUrl)
        }

        viewModel.followersLiveData.observe(viewLifecycleOwner) { list ->
            followersAdapter.submitList(list)
            viewModel.fetchAllFollowerNames(list)
        }

        viewModel.followerName.observe(viewLifecycleOwner) { namesMap ->
            followersAdapter.setNamesMap(namesMap)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorMsg ->
            toast(errorMsg)
        }
    }

    private fun setupRecyclerView() {
        binding.followersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.followersRecyclerView.adapter = followersAdapter
    }
}