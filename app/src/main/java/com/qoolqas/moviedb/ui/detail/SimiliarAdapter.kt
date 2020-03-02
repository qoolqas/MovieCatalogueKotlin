package com.qoolqas.moviedb.ui.detail

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qoolqas.moviedb.R
import com.qoolqas.moviedb.model.similiar.SimiliarResultsItem
import com.qoolqas.moviedb.ui.home.PopularAdapter
import kotlinx.android.synthetic.main.item_card_similiar.view.*

class SimiliarAdapter(private val list: List<SimiliarResultsItem>) :
    RecyclerView.Adapter<SimiliarAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_similiar, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.similiarTitle.text = list[position].title
        Glide.with(holder.view)
            .load("https://image.tmdb.org/t/p/w185" + list[position].posterPath)
            .placeholder(R.color.gray)
            .into(holder.view.similiarPoster)

        holder.view.setOnClickListener {
            val intentSimiliar = Intent(holder.view.context, DetailActivity::class.java)
            intentSimiliar.putExtra(DetailActivity.EXTRA_ID, list[position].id)
            holder.view.context.startActivity(intentSimiliar)
            Log.d("id", list[position].id.toString())

        }
    }
}