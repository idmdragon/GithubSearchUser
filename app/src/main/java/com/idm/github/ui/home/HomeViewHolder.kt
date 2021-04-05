package com.idm.github.ui.home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.idm.github.R
import com.idm.github.databinding.ItemLayoutBinding
import com.idm.github.models.User
import com.idm.github.ui.detail.DetailActivity

class HomeViewHolder (private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            tvName.text = user.login

            Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .placeholder(ColorDrawable(Color.GRAY))
                    .error(R.drawable.ic_error)
                    .apply(RequestOptions().override(57, 57))
                    .into(ivPhoto)


            btnDetail.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.NAME_KEY, user.login)
                itemView.context.startActivity(intent)
            }

        }

    }
}


