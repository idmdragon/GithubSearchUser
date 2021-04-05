package com.idm.github.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.idm.github.databinding.ActivityFavoriteBinding
import com.idm.github.models.User
import com.idm.github.ui.detail.DetailActivity
import com.idm.github.ui.home.ListUserAdapter
import com.idm.github.ui.home.OnItemClickCallback
import com.idm.github.utils.ViewModelRefactory

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: ListUserAdapter
    val factory by lazy {
        val app = application
        ViewModelRefactory(app)
    }
    private val favoriteViewModel by viewModels<FavoriteViewModel> { factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.rvFav.layoutManager = LinearLayoutManager(this)
        favoriteViewModel.getAllData().observe(this, ::receiver)

    }

    private fun receiver(observer: List<User>) {

        binding.dataNotFound.isVisible = observer.isEmpty()

        val listUser = ArrayList<User>()
        listUser.addAll(observer)
        favoriteAdapter = ListUserAdapter(listUser)
        favoriteAdapter.notifyDataSetChanged()
        binding.rvFav.adapter = favoriteAdapter

        favoriteAdapter.setOnItemCallback(
            object : OnItemClickCallback {
                override fun onItemClicked(user: User) {
                    val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NAME_KEY, user.login)
                    startActivity(intent)
                }
            }
        )
    }


}