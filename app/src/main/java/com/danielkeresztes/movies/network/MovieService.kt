package com.danielkeresztes.movies.network

import com.danielkeresztes.movies.data.remote.ConfigurationResponse
import com.danielkeresztes.movies.data.remote.MoviesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    companion object {

        const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): MovieService {
            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client : OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build()

            return retrofit.create(MovieService::class.java)
        }
    }

    @GET("movie/now_playing")
    fun nowPlaying(@Query("api_key") apiKey: String,
                      @Query("language") language: String,
                      @Query("page") page: Int):
            Single<MoviesResponse>

    @GET("configuration")
    fun configuration(@Query("api_key") apiKey: String): Single<ConfigurationResponse>

}

