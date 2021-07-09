package com.shubhamkumarwinner.multipleviewrecyclerview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shubhamkumarwinner.multipleviewrecyclerview.R
import com.shubhamkumarwinner.multipleviewrecyclerview.databinding.ItemDirectorBinding
import com.shubhamkumarwinner.multipleviewrecyclerview.databinding.ItemMovieBinding
import com.shubhamkumarwinner.multipleviewrecyclerview.databinding.ItemTitleBinding

class HomeRecyclerViewAdapter(val onClickListener: OnClickListener): ListAdapter<HomeRecyclerViewItem, HomeRecyclerViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when(viewType){
            R.layout.item_title -> HomeRecyclerViewHolder.TitleViewHolder(ItemTitleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            R.layout.item_movie -> HomeRecyclerViewHolder.MovieViewHolder(ItemMovieBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))
            R.layout.item_director -> HomeRecyclerViewHolder.DirectorViewHolder(ItemDirectorBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is HomeRecyclerViewHolder.DirectorViewHolder -> holder.bind(item as HomeRecyclerViewItem.Director)
            is HomeRecyclerViewHolder.MovieViewHolder -> holder.bind(item as HomeRecyclerViewItem.Movie)
            is HomeRecyclerViewHolder.TitleViewHolder -> holder.bind(item as HomeRecyclerViewItem.Title)
        }

        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is HomeRecyclerViewItem.Director -> R.layout.item_director
            is HomeRecyclerViewItem.Movie -> R.layout.item_movie
            is HomeRecyclerViewItem.Title -> R.layout.item_title
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<HomeRecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: HomeRecyclerViewItem, newItem: HomeRecyclerViewItem): Boolean{
                return (oldItem is HomeRecyclerViewItem.Director && newItem is HomeRecyclerViewItem.Director &&
                        oldItem.name == newItem.name) ||
                        (oldItem is HomeRecyclerViewItem.Movie && newItem is HomeRecyclerViewItem.Movie &&
                                oldItem.thumbnail == newItem.thumbnail) ||
                        (oldItem is HomeRecyclerViewItem.Title && newItem is HomeRecyclerViewItem.Title &&
                                oldItem.title == newItem.title)
            }

            override fun areContentsTheSame(oldItem: HomeRecyclerViewItem, newItem: HomeRecyclerViewItem): Boolean =
                oldItem == newItem
        }
    }
}

class OnClickListener(val clickListener: (homeRecyclerViewItem: HomeRecyclerViewItem) -> Unit) {
    fun onClick(homeRecyclerViewItem: HomeRecyclerViewItem) = clickListener(homeRecyclerViewItem)
}