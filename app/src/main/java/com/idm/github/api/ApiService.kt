package com.idm.github.api

import com.idm.github.models.SearchResponse
import com.idm.github.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
   suspend fun searchUsers(
        @Query("q")
        search:String)
    : Response<SearchResponse>


    @GET("users/{username}")
    suspend fun getDetail(
            @Path("username")
            username: String
    ): Response<User>

    @GET("users/{username}/followers")
    suspend fun getFollowers(
            @Path("username")
            username: String
    ): Response<ArrayList<User>>


    @GET("users/{username}/following")
    suspend fun getFollowing(
            @Path("username")
            username: String
    ): Response<ArrayList<User>>

}