package com.aefremov.newsapi.models

import kotlinx.serialization.SerialName

enum class SortBy {

    @SerialName("relevancy")
    RELEVANCY,

    @SerialName("popularity")
    POPULARITY,

    @SerialName("published_At")
    PUBLISHED_AT,

}