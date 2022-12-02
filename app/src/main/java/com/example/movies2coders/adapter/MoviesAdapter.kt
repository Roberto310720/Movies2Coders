package com.example.movies2coders.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies2coders.R
import com.example.movies2coders.model.Movie
import com.example.movies2coders.remote.ApiService

class MoviesAdapter(val onClick: (Movie) -> Unit): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var data = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(movieData: List<Movie>) {
        data = movieData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.movieTitle)
        val card = itemView.findViewById<CardView>(R.id.movieCard)
        val imgMovie = itemView.findViewById<ImageView>(R.id.moviePoster)

        fun bind(item: Movie) {
            title.text = item.title

            val urlImage = ApiService.URL_IMAGES + item.poster_path
            Glide.with(card).load(urlImage).into(imgMovie)

            card.setOnClickListener {
                Log.v("Pulso sobre", item.id.toString())
                onClick(item)
            }
        }
    }
}