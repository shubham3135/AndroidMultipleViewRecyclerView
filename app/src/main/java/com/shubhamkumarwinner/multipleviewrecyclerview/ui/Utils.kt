package com.shubhamkumarwinner.multipleviewrecyclerview.ui

import android.app.Activity
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.shubhamkumarwinner.multipleviewrecyclerview.R

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<HomeRecyclerViewItem>?){
//    val adapter = recyclerView.adapter as HomeRecyclerViewAdapter
//    adapter.submitList(data)
//}

@BindingAdapter("movieNum")
fun bindMovieNumber(textView: TextView, num: Int?){
    textView.text = "$num movies"
}

fun Activity.snackbar(msg: String, action: (() -> Unit)? = null) {
    Snackbar.make(
        findViewById(android.R.id.content),
        msg,
        Snackbar.LENGTH_LONG
    ).also {
        it.setAction(
            getString(R.string.ok)
        ) { action?.invoke() }
    }.show()
}