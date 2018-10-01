package com.danielkeresztes.movies.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse (
        @Json(name = "page") val page: Int,
        @Json(name = "results") val movies: List<Movie>,
        @Json(name = "dates") var dates: Dates?,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int
)