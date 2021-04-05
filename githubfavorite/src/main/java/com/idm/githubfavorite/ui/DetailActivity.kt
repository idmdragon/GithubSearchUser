package com.idm.githubfavorite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.idm.githubfavorite.databinding.ActivityDetailBinding
import com.idm.githubfavorite.models.User

class DetailActivity : AppCompatActivity() {

    companion object{
        var PARCELABLE_KEY = "parcelable_key"
    }
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        val user = intent.getParcelableExtra<User>(PARCELABLE_KEY)

        if (user != null) {
            binding.tvCompany.text = user.company
            binding.tvFollowers.text = user.followers.toString()
            binding.tvFollowing.text = user.following.toString()
            binding.tvLocation.text = user.location
            binding.tvName.text = user.name
            binding.tvUserid.text = user.login
            binding.tvRepository.text = user.public_repos.toString()

            Glide.with(this)
                .load(user.avatar_url)
                .apply(RequestOptions().override(95, 95))
                .into(binding.ivPhoto)
        }


    }

}