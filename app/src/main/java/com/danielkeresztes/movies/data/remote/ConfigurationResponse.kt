package com.danielkeresztes.movies.data.remote

import android.provider.MediaStore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ConfigurationResponse (
        @Json(name = "images") val images: MediaStore.Images,
        @Json(name = "changeKeys") val changeKeys: List<String>
)