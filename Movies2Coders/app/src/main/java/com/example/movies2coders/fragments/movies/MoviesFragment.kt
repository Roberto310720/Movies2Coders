package com.example.movies2coders.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies2coders.DataHolder
import com.example.movies2coders.R
import kotlinx.coroutines.launch
import com.example.movies2coders.adapter.MoviesAdapter

class MoviesFragment : Fragment() {
    private val viewModel: MoviesViewModel by activityViewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressDialogMovies)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                //loading bar visibility
                launch {
                    viewModel.loading.collect { loading ->
                        if (loading) {
                            progressBar.visibility = View.VISIBLE
                        } else {
                            progressBar.visibility = View.GONE
                        }
                    }
                }

                //get the movies
                launch {
                    viewModel.moviesList.collect {
                        adapter.updateData(it.results)
                    }
                }

            }
        }

        //when a movie is clicked we change the 
        adapter = MoviesAdapter {
            DataHolder.movie = it
            findNavController().navigate(R.id.action_homeFragment_to_detailMovieFragment)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter

        viewModel.getMovies()
    }
}