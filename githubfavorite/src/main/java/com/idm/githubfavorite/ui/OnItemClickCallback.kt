package com.idm.githubfavorite.ui

import com.idm.githubfavorite.models.User


interface OnItemClickCallback {
    fun onItemClicked(user : User)
}