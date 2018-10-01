package com.danielkeresztes.movies.moviedetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val ARG_TITLE: String = "ARG_TITLE"
        const val ARG_IMG_URL: String = "ARG_IMG_URL"
        const val ARG_OVERVIEW: String = "ARG_OVERVIEW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}