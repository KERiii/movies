package com.danielkeresztes.movies.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.danielkeresztes.movies.R
import com.danielkeresztes.movies.data.remote.Movie
import com.danielkeresztes.movies.data.remote.MoviesResponse
import com.danielkeresztes.movies.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MoviesActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.loadMovies().observe(this, Observer<MoviesResponse> { response ->
                if (response != null) {
                    moviesAdapter = MoviesAdapter(response.movies) { movie: Movie -> openDetailsActivity(movie)}
                    movieRecyclerView.setHasFixedSize(true)
                    movieRecyclerView.layoutManager = GridLayoutManager(this, 2)
                    movieRecyclerView.adapter = moviesAdapter
                }
        })

    }

    fun openDetailsActivity(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.ARG_IMG_URL, movie.posterPath)
        intent.putExtra(MovieDetailActivity.ARG_TITLE, movie.title)
        intent.putExtra(MovieDetailActivity.ARG_OVERVIEW, movie.overview)
        startActivity(intent)
    }
}
