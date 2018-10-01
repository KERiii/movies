package com.danielkeresztes.movies.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Images (

        @Json(name = "baseUrl") val baseUrl: String,
        @Json(name = "secureBaseUrl") val secureBaseUrl: String,
        @Json(name = "backdropSizes") val backdropSizes: List<String>,
        @Json(name = "logoSizes") val logoSizes: List<String>,
        @Json(name = "posterSizes") val posterSizes: List<String>,
        @Json(name = "profileSizes") val profileSizes: List<String>,
        @Json(name = "stillSizes") val stillSizes: List<String>

)