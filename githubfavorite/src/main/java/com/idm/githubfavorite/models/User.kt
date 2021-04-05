package com.idm.githubfavorite.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_favorite")
@Parcelize
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String?,
    @ColumnInfo(name = "company")
    val company: String ?,
    @ColumnInfo(name = "followers")
    val followers: Int?,
    @ColumnInfo(name = "followers_url")
    val followers_url : String?,
    @ColumnInfo(name = "following")
    val following: Int?,
    @ColumnInfo(name = "following_url")
    val following_url: String?,
    @ColumnInfo(name = "gravatar_id")
    val gravatar_id: String?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "login")
    val login: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "node_id")
    val node_id: String?,
    @ColumnInfo(name = "public_repos")
    val public_repos: Int?
):Parcelable