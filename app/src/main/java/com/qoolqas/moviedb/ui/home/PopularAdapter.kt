package com.qoolqas.moviedb.ui.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qoolqas.moviedb.R
import com.qoolqas.moviedb.model.popular.PopularResultsItem
import com.qoolqas.moviedb.ui.DetailActivity
import com.qoolqas.moviedb.ui.DetailActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.item_card_discover.view.*
import kotlinx.android.synthetic.main.item_card_popular.view.*

class PopularAdapter(private val list: List<PopularResultsItem>) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_popular, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.popularTitle.text = list.get(position).title
        holder.view.popularRating.text = list.get(position).voteAverage.toString()
        Glide.with(holder.view)
            .load("https://image.tmdb.org/t/p/w185" + list.get(position).posterPath)
            .placeholder(R.color.gray)
            .into(holder.view.popularPoster)

        holder.view.setOnClickListener(View.OnClickListener {
            val intentDetail = Intent(holder.view.context, DetailActivity::class.java)
            intentDetail.putExtra(EXTRA_ID, list.get(position).id)
            holder.view.context.startActivity(intentDetail)
            Log.d("id", list.get(position).id.toString())

        })
    }

}