package com.idm.githubfavorite.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.idm.githubfavorite.databinding.ItemLayoutBinding
import com.idm.githubfavorite.models.User


class MainViewHolder (private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            tvName.text = user.login
            tvUsername.text = user.name
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .placeholder(ColorDrawable(Color.GRAY))
                .apply(RequestOptions().override(57, 57))
                .into(ivPhoto)


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.PARCELABLE_KEY, user)
                itemView.context.startActivity(intent)
            }
            btnDetail.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.PARCELABLE_KEY, user)
                itemView.context.startActivity(intent)
            }

        }

    }
}


