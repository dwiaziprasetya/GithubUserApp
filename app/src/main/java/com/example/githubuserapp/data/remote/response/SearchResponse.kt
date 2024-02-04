package com.example.githubuserapp.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class SearchResponse(
	val totalCount: Int,
	val incompleteResults: Boolean,
	val items: List<ItemsItem>
)

@Parcelize
data class ItemsItem(
	val gistsUrl: String,
	val reposUrl: String,
	val followingUrl: String,
	val starredUrl: String,
	val login: String,
	val followersUrl: String,
	val type: String,
	val url: String,
	val subscriptionsUrl: String,
	val score: @RawValue Any,
	val receivedEventsUrl: String,
	val avatarUrl: String,
	val eventsUrl: String,
	val htmlUrl: String,
	val siteAdmin: Boolean,
	val id: Int,
	val gravatarId: String,
	val nodeId: String,
	val organizationsUrl: String
) : Parcelable

