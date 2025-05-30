package com.example.appgithubapioauth2.app.ui.followers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.appgithubapioauth2.R
import com.example.appgithubapioauth2.app.data.model.GithubFollowers
import com.example.appgithubapioauth2.databinding.ItemFollowersBinding

class FollowersAdapter : ListAdapter<GithubFollowers, FollowersAdapter.FollowersViewHolder>(FollowersDiffCallback()) {

    private var namesMap: Map<String, String?> = emptyMap()

    fun setNamesMap(map: Map<String, String?>) {
        namesMap = map
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding = ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val follower = getItem(position)
        val realName = namesMap[follower.login]
        holder.bind(follower, realName)
    }

    class FollowersViewHolder(
        private val binding: ItemFollowersBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(follower: GithubFollowers, realName: String?) {
            binding.tvFollowers.text = realName ?: follower.login
            binding.imageFollowers.load(follower.avatarUrl) {
                transformations(CircleCropTransformation())
                placeholder(R.drawable.github_logo)
                error(R.drawable.github_logo)
            }
        }
    }

    class FollowersDiffCallback : DiffUtil.ItemCallback<GithubFollowers>() {
        override fun areItemsTheSame(oldItem: GithubFollowers, newItem: GithubFollowers): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubFollowers, newItem: GithubFollowers): Boolean {
            return oldItem == newItem
        }
    }
}