package com.androidshowtime.dzoneproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidshowtime.dzoneproject.data.Movie


class RecyclerViewAdapter(private val myDataSet: List<Movie>) : RecyclerView
                                                                .Adapter<RecyclerViewAdapter
.MovieViewHolder>() {

    //inflates XML layout and returns ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_item, parent,
                    false
            )

        return MovieViewHolder(view)
    }

    //tells the adapter the number of movie items to display
    override fun getItemCount(): Int {
        return myDataSet.size
    }

    /*fetches and updates the contents of itemView to reflect the movie
    title in a given position*/
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.tvName.text = myDataSet[position].name

        //name is from the Movie Object property
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView = itemView.findViewById(R.id.tvName)

    }


}








