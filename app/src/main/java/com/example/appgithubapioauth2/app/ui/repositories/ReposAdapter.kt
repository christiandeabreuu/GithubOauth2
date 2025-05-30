package com.example.appgithubapioauth2.app.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appgithubapioauth2.app.data.model.GithubRepo
import com.example.appgithubapioauth2.databinding.ItemRepoBinding

class ReposAdapter : ListAdapter<GithubRepo, ReposAdapter.ReposViewHolder>(ReposDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    class ReposViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: GithubRepo) {
            binding.repoName.text = repo.name
            binding.repoDescription.text = repo.description ?: ""
        }
    }

    class ReposDiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
        override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
            return oldItem == newItem
        }
    }
}