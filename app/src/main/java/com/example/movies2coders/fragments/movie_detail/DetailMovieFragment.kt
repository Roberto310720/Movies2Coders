package com.example.movies2coders.fragments.movie_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movies2coders.DataHolder
import com.example.movies2coders.R
import com.example.movies2coders.remote.ApiService
import kotlinx.coroutines.launch

class DetailMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailMovieFragment_to_homeFragment)
        }

        val movie = DataHolder.movie

        val imgMovie = view.findViewById<ImageView>(R.id.movieDetailPoster)
        val card = view.findViewById<CardView>(R.id.movieDetailCard)
        val title = view.findViewById<TextView>(R.id.movieDetailTitle)
        val description = view.findViewById<TextView>(R.id.movieDetailDescription)

        val urlImage = ApiService.URL_IMAGES + movie.poster_path
        Glide.with(card).load(urlImage).into(imgMovie)
        title.text = movie.title
        description.text = movie.overview
    }

}