package com.idm.github.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idm.github.databinding.ItemLayoutBinding
import com.idm.github.models.User

class ListUserAdapter(private val listUser : ArrayList<User>) : RecyclerView.Adapter<HomeViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemUserBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount()= listUser.size
}