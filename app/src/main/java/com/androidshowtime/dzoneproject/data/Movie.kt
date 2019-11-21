package com.androidshowtime.dzoneproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo
        var name: String =" "
) {
}