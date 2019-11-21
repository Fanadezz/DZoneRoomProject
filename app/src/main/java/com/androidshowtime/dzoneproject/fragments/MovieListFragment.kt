package com.androidshowtime.dzoneproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidshowtime.dzoneproject.AppDatabase
import com.androidshowtime.dzoneproject.NewMovieViewModel
import com.androidshowtime.dzoneproject.R
import com.androidshowtime.dzoneproject.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber


class MovieListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var viewModel: NewMovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewMovieViewModel::class.java)
        viewModel.retrieveMovie().observe(this, Observer {

            Timber.i("received the movies ${it.size}")
            recyclerViewList.adapter = RecyclerViewAdapter(it)
        })

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun goToNewMovieFragment()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnAdd.setOnClickListener(View.OnClickListener {


            val dao =  AppDatabase.getInstance(this.context!!)?.movieDao()

            GlobalScope.launch { dao?.getAll() }

            listener?.goToNewMovieFragment()

        })

        recyclerViewList.layoutManager = LinearLayoutManager(activity)
    }
}
