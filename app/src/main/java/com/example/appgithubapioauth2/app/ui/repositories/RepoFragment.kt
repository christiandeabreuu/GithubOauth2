package com.example.appgithubapioauth2.app.ui.repositories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgithubapioauth2.app.ui.createrepositories.CreateRepoActivity
import com.example.appgithubapioauth2.databinding.FragmentRepoBinding
import com.example.appgithubapioauth2.utils.NetworkUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.appgithubapioauth2.utils.toast

class RepoFragment : Fragment() {

    private lateinit var binding: FragmentRepoBinding
    private val viewModel: RepoViewModel by viewModel()
    private lateinit var reposAdapter: ReposAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reposAdapter = ReposAdapter()
        setupCreateRepoButton()
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.reposRecyclerView.adapter = reposAdapter

        viewModel.reposLiveData.observe(viewLifecycleOwner) { list ->
            reposAdapter.submitList(list)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { msg ->
            toast(msg)
        }

        viewModel.getAuthenticatedUserRepos()
    }

    private fun setupCreateRepoButton() {
        binding.buttonToGoCreatePlaylist.setOnClickListener {
            NetworkUtils.isInternetAvailable(requireContext())
            val intent = Intent(requireContext(), CreateRepoActivity::class.java)
            startActivity(intent)
        }
    }
}