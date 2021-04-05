package com.idm.github.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.idm.github.R
import com.idm.github.databinding.ActivityDetailBinding
import com.idm.github.ui.detail.follow.SectionsPagerAdapter
import com.idm.github.utils.ViewModelRefactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    val factory by lazy {
        val app = application
        ViewModelRefactory(app)
    }
    private val detailViewModel by viewModels<DetailViewModel> { factory }

    companion object {

        var NAME_KEY = "name_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var favState = false

        binding.btnBack.setOnClickListener {
            finish()
        }
        val userName = intent.getStringExtra(NAME_KEY)

        if (userName != null) {
            detailViewModel.getDetailUser(userName)
            setPager(userName)
        }
        setLoading(true)
        setView()

        if (userName !=null){
            detailViewModel.getUserAccount(userName).observe(this){
                if (it!=null){
                    favState = true
                    setFavorite(true)
                }
            }
        }


        binding.btnFav.setOnClickListener {
            if (favState == false) {
                setFavorite(true)
                Snackbar.make(it, "User Saved to Favorite", Snackbar.LENGTH_SHORT).show()
                detailViewModel.upsert()
                
                favState = true
            } else {
                setFavorite(false)
                Snackbar.make(it, "User Deleted from Favorite", Snackbar.LENGTH_SHORT).show()
                detailViewModel.delete()
                favState = false
            }
        }

    }

    private fun setFavorite(state: Boolean) {
        if (state == true) {
            binding.btnFav.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_fav_filled
                )
            )

        } else if (state == false) {
            binding.btnFav.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_baseline_favorite
                )
            )
        }
    }

    private fun setLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun setPager(username: String) {

        val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailActivity, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        sectionsPagerAdapter.username = username
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }


    private fun setView() {
        detailViewModel.user.observe(this) {
            setLoading(false)

            binding.tvCompany.text = it.company
            binding.tvFollowers.text = it.followers.toString()
            binding.tvFollowing.text = it.following.toString()
            binding.tvLocation.text = it.location
            binding.tvName.text = it.name
            binding.tvUserid.text = it.login
            binding.tvRepository.text = it.public_repos.toString()

            Glide.with(this)
                .load(it.avatar_url)
                .placeholder(ColorDrawable(Color.GRAY))
                .error(R.drawable.ic_error)
                .apply(RequestOptions().override(95, 95))
                .into(binding.ivPhoto)
        }
    }

}