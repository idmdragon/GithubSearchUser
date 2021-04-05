package com.idm.github.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.idm.github.R
import com.idm.github.databinding.ActivityHomeBinding
import com.idm.github.models.User
import com.idm.github.ui.detail.DetailActivity
import com.idm.github.ui.favorite.FavoriteActivity
import com.idm.github.ui.setting.SettingActivity


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var listUserAdapter: ListUserAdapter
    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar()

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        homeViewModel.listUser.observe(this,::receiver)
    }

    private fun toolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbar.inflateMenu(R.menu.my_menu)
        binding.toolbar.elevation = 0F
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.my_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    progressBar(false)
                }else{
                    homeViewModel.searchUser(query)
                    progressBar(true)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    progressBar(false)
                } else{
                    homeViewModel.searchUser(newText)
                    progressBar(true)
                }
                return false
            }
        })
        return true
    }

    private fun receiver(observer : ArrayList<User>){
        binding.searchIlustration.isVisible = false
        progressBar(false)
        if (observer.isEmpty()){
            binding.notfoundIlustration.isVisible = true
            binding.searchIlustration.isVisible = false
        }else{
            binding.notfoundIlustration.isVisible = false
        }
        listUserAdapter = ListUserAdapter(observer)
        listUserAdapter.notifyDataSetChanged()
        binding.rvUser.adapter = listUserAdapter
        if(listUserAdapter.itemCount!=0){
            binding.searchIlustration.isVisible = false
        }
        listUserAdapter.setOnItemCallback(
            object : OnItemClickCallback{
                override fun onItemClicked(user: User) {
                    val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NAME_KEY,user.login)
                    startActivity(intent)
                }
            }
        )
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                startActivity(Intent(this@HomeActivity,SettingActivity::class.java))
                true
            }
            R.id.favorite ->{
                startActivity(Intent(this@HomeActivity,FavoriteActivity::class.java))
                true
            }
            else -> true
        }
    }

    private fun progressBar(state: Boolean) {
            binding.progressBar.isVisible = state
    }
}




