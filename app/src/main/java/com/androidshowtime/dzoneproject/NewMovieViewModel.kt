package com.androidshowtime.dzoneproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidshowtime.dzoneproject.data.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewMovieViewModel(application: Application) : AndroidViewModel(application) {


    private val db: AppDatabase? = AppDatabase.getInstance(application)
    private val allMovies = MutableLiveData<List<Movie>>()


    fun retrieveMovie(): LiveData<List<Movie>> {
        GlobalScope.launch {

            val list = db?.movieDao()?.getAll()
            Timber.e("retrieveMovies list count ${list?.size}")

            //notifies the observers(i.e. the fragments) of the fetched data
            allMovies.postValue(list)

        }
        return allMovies
    }


    fun storeMovie(title: String) {

        val movie = Movie()
        movie.name = title

        GlobalScope.launch {

            db?.movieDao()?.insert(movie)

        }


    }
}











