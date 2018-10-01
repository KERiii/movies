package com.danielkeresztes.movies.movies

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.danielkeresztes.movies.data.remote.MoviesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable


class MoviesViewModel(context: Application) : AndroidViewModel(context) {

    private val moviesRepository: MoviesRepository = MoviesRepository()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loadMovies(): LiveData<MoviesResponse> {
        val moviesMutableLiveData: MutableLiveData<MoviesResponse> = MutableLiveData()

        compositeDisposable.add(moviesRepository
                .getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response -> moviesMutableLiveData.postValue(response)})


        return moviesMutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}