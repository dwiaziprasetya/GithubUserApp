package com.example.githubuserapp.retrofit

import com.example.githubuserapp.response.DetailResponse
import com.example.githubuserapp.response.FollowersResponseItem
import com.example.githubuserapp.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/search/users")
    @Headers("Authorization: token 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")
    fun getSearch(
        @Query("q") username :String
    ) : Call<SearchResponse>

    @GET("/users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailPerson(
        @Path("username") username: String
    ) : Call<DetailResponse>

    @GET("/users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<FollowersResponseItem>>

    @GET("/users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<FollowersResponseItem>>
}