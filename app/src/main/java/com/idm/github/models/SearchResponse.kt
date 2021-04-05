package com.idm.github.models


data class SearchResponse(
    val incomplete_results: Boolean,
    val items: ArrayList<User>,
    val total_count: Int
)