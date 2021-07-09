package com.shubhamkumarwinner.multipleviewrecyclerview.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.shubhamkumarwinner.multipleviewrecyclerview.databinding.ItemDirectorBinding
import com.shubhamkumarwinner.multipleviewrecyclerview.databinding.ItemMovieBinding
import com.shubhamkumarwinner.multipleviewrecyclerview.databinding.ItemTitleBinding

sealed class HomeRecyclerViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    class TitleViewHolder(private val binding: ItemTitleBinding): HomeRecyclerViewHolder(binding){
        fun bind(title: HomeRecyclerViewItem.Title){
            binding.textViewTitle.text = title.title
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding): HomeRecyclerViewHolder(binding){
        fun bind(movie: HomeRecyclerViewItem.Movie){
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    class DirectorViewHolder(private val binding: ItemDirectorBinding): HomeRecyclerViewHolder(binding){
        fun bind(director: HomeRecyclerViewItem.Director){
            binding.director  = director
        }
    }




}