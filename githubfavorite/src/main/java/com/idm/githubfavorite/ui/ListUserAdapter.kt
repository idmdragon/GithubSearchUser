package com.idm.githubfavorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idm.githubfavorite.databinding.ItemLayoutBinding
import com.idm.githubfavorite.models.User

class ListUserAdapter(private val listUser : ArrayList<User>) : RecyclerView.Adapter<MainViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemUserBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount()= listUser.size
}