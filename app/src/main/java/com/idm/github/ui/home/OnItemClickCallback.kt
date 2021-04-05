package com.idm.github.ui.home

import com.idm.github.models.User

interface OnItemClickCallback {
    fun onItemClicked(user : User)
}