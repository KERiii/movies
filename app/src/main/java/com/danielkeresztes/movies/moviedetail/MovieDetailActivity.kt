package com.danielkeresztes.movies.moviedetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.danielkeresztes.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val ARG_TITLE: String = "ARG_TITLE"
        const val ARG_IMG_URL: String = "ARG_IMG_URL"
        const val ARG_OVERVIEW: String = "ARG_OVERVIEW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    private fun init() {
        val backDrop = intent.getStringExtra(ARG_IMG_URL)
        val title = intent.getStringExtra(ARG_TITLE)
        val overview = intent.getStringExtra(ARG_OVERVIEW)
        if (backDrop != null) {
            Picasso.get().load("http://image.tmdb.org/t/p/w1280$backDrop").into(movieDetailsImage)
        }
        movieDetailsTitle.text = title
        movieDetailsOverview.text = overview
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            if (item.itemId == android.R.id.home) {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}