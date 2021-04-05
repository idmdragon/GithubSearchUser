package com.idm.github.ui.detail.follow

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.idm.github.R
import com.idm.github.databinding.ItemFollowBinding
import com.idm.github.models.User

class FollowViewHolder(private val binding: ItemFollowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.apply {
            tvName.text = user.login
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .apply(RequestOptions().override(57, 57))
                .placeholder(ColorDrawable(Color.GRAY))
                .error(R.drawable.ic_error)
                .into(
                    ivPhoto
                )

        }
    }
}