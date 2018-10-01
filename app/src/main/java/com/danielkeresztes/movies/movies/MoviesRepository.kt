package com.danielkeresztes.movies.movies

import com.danielkeresztes.movies.data.remote.ConfigurationResponse
import com.danielkeresztes.movies.data.remote.MoviesResponse
import com.danielkeresztes.movies.network.MovieService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class MoviesRepository {

    companion object {
        const val API_KEY: String = "c8373d0d8fd24037653947c11528e21b"
    }

    val movieService by lazy {
        MovieService.create()
    }

    fun getMovies(): Single<MoviesResponse> {
        return movieService.nowPlaying(API_KEY, "en-US", 1)
                .subscribeOn(Schedulers.io())
    }

    fun getConfig(): Single<ConfigurationResponse> {
        return movieService.configuration(API_KEY)
                .subscribeOn(Schedulers.io())
    }
}