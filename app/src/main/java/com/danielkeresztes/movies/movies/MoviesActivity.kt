package com.danielkeresztes.movies.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.ArrayAdapter
import com.danielkeresztes.movies.R
import com.danielkeresztes.movies.data.remote.Movie
import com.danielkeresztes.movies.data.remote.MoviesResponse
import com.danielkeresztes.movies.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MoviesActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private var searchResults: List<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.loadMovies().observe(this, Observer<MoviesResponse> { response ->
            if (response != null) {
                moviesAdapter = MoviesAdapter(response.movies) { movie: Movie -> openDetailsActivity(movie) }
                movieRecyclerView.setHasFixedSize(true)
                movieRecyclerView.layoutManager = GridLayoutManager(this, 2)
                movieRecyclerView.adapter = moviesAdapter
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu?.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem?.actionView as SearchView
        val searchAutocomplete: SearchView.SearchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        searchAutocomplete.setAdapter(adapter)
        searchAutocomplete.dropDownAnchor = R.id.actionSearch

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                openDetailsActivity(searchResults[position])
                return true
            }

        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (!it.isNullOrBlank()) {
                        viewModel.search(it).observe(this@MoviesActivity, Observer<MoviesResponse> { response ->
                            adapter.clear()
                            val searchTitles = ArrayList<String>()
                            if (response != null) {
                                searchResults = response.movies
                            }
                            response?.movies?.forEach { movie -> searchTitles.add(movie.originalTitle) }
                            adapter.addAll(searchTitles)
                        })
                    }
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    fun openDetailsActivity(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.ARG_IMG_URL, movie.backdropPath)
        intent.putExtra(MovieDetailActivity.ARG_TITLE, movie.title)
        intent.putExtra(MovieDetailActivity.ARG_OVERVIEW, movie.overview)
        startActivity(intent)
    }
}
