package com.idm.github.ui.detail.follow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idm.github.databinding.ItemFollowBinding
import com.idm.github.models.User

class FollowAdapter (private val listFollow : ArrayList<User>): RecyclerView.Adapter<FollowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val itemFollowBinding = ItemFollowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowViewHolder(itemFollowBinding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        val follow = listFollow[position]
        holder.bind(follow)
    }

    override fun getItemCount() = listFollow.size
}