package com.idm.github.repository

import com.idm.github.api.RetrofitInstance

class ApiUserRepository {
    suspend fun searchUser(Query : String) =
        RetrofitInstance.api.searchUsers(search = Query)

    suspend fun getDetailUser(Keyword : String) =
        RetrofitInstance.api.getDetail(username = Keyword)

    suspend fun getFollowersUser(keyword: String) =
        RetrofitInstance.api.getFollowers(keyword)

    suspend fun getFollowingUser(keyword: String) =
        RetrofitInstance.api.getFollowing(keyword)

}

