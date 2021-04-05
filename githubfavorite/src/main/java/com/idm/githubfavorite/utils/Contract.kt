package com.idm.githubfavorite.utils

import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.idm.githubfavorite.models.User

object Contract {
    const val AUTHORITY = "com.idm.github"
    const val SCHEME = "content"
    const val TABLE_NAME = "user_favorite"


    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()

    fun ConvertCursor(cursor : Cursor):ArrayList<User>{
        val listUser = ArrayList<User>()

        cursor.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val avatar_url = getString(getColumnIndexOrThrow("avatar_url"))
                Log.d("Contract","Isi avatar url $avatar_url")
                val company = getString(getColumnIndexOrThrow("company"))
                val followers = getInt(getColumnIndexOrThrow("followers"))
                val followers_url = getString(getColumnIndexOrThrow("followers_url"))
                val following = getInt(getColumnIndexOrThrow("following"))
                val followingUrl = getString(getColumnIndexOrThrow("following_url"))
                val gravatar_id = getString(getColumnIndexOrThrow("gravatar_id"))
                val location = getString(getColumnIndexOrThrow("location"))
                val login = getString(getColumnIndexOrThrow("login"))
                val name = getString(getColumnIndexOrThrow("name"))
                val node_id = getString(getColumnIndexOrThrow("node_id"))
                val public_repos = getInt(getColumnIndexOrThrow("public_repos"))
                listUser.add(
                    User(id,avatar_url,company,followers,followers_url,following,
                        followingUrl,gravatar_id,location,login,name,node_id,public_repos)
                )
            }
        }
        return listUser
    }
}