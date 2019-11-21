package com.androidshowtime.dzoneproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao

interface MovieDao {

    @Query("select * from Movie")
    fun getAll(): List<Movie>

   // fun getAll(): LiveData<List<Movie>>


    @Insert
    fun insert(item: Movie)

}




