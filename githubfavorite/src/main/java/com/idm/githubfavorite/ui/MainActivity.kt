package com.idm.githubfavorite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.Cursor
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.idm.githubfavorite.databinding.ActivityMainBinding
import com.idm.githubfavorite.models.User
import com.idm.githubfavorite.utils.Contract.CONTENT_URI
import com.idm.githubfavorite.utils.Contract.ConvertCursor


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listUserAdapter: ListUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        val content = this.contentResolver
        val cursor = content.query(
            CONTENT_URI,
            null,
            null,
            null,
            null
        )

        if (cursor != null && cursor.count > 0) {
            setData(cursor)
        }else{
            binding.dataNotFound.isVisible = true
        }
    }

    private fun setData(cursor: Cursor) {
        with(binding){
            dataNotFound.isVisible = false
            rvFav.layoutManager = LinearLayoutManager(this@MainActivity)
            listUserAdapter = ListUserAdapter(ConvertCursor(cursor))
            rvFav.adapter = listUserAdapter
            listUserAdapter.notifyDataSetChanged()
            listUserAdapter.setOnItemCallback(
                object : OnItemClickCallback{
                    override fun onItemClicked(user: User) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.PARCELABLE_KEY,user)
                        startActivity(intent)
                    }
                }
            )

        }

    }
}