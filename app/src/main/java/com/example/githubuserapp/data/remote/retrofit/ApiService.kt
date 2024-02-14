package com.example.githubuserapp.data.remote.retrofit

import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.data.remote.response.DetailResponse
import com.example.githubuserapp.data.remote.response.FollowersResponseItem
import com.example.githubuserapp.data.remote.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/search/users")
    @Headers("Authorization: token "  + BuildConfig.TOKEN)
    fun getSearch(
        @Query("q") username :String
    ) : Call<SearchResponse>

    @GET("/users/{username}")
        @Headers("Authorization: token " + BuildConfig.TOKEN)
    fun getDetailPerson(
        @Path("username") username: String
    ) : Call<DetailResponse>

    @GET("/users/{username}/followers")
    @Headers("Authorization: token " + BuildConfig.TOKEN)
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<FollowersResponseItem>>

    @GET("/users/{username}/following")
    @Headers("Authorization: token " + BuildConfig.TOKEN)
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<FollowersResponseItem>>
}