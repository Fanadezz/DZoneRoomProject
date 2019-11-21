package com.androidshowtime.dzoneproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.androidshowtime.dzoneproject.NewMovieViewModel
import com.androidshowtime.dzoneproject.R
import kotlinx.android.synthetic.main.fragment_new_movie.*




class NewMovieFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

private lateinit var viewModel: NewMovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       viewModel = ViewModelProviders.of(this).get(NewMovieViewModel::class.java)


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_movie, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(
                    context.toString() + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {

        fun goToMovieListFragment()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NewMovieFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener(View.OnClickListener {

            val input = editText.text.toString().trim()

            if (input.isEmpty()) {
                Toast.makeText(activity, "Title required", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (input.length > 30) {
                Toast.makeText(activity, "Title too long", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }



         viewModel.storeMovie(input)

            Toast.makeText(activity, "$input entered", Toast.LENGTH_SHORT).show()
            listener?.goToMovieListFragment()
        })
    }
}
