package com.qoolqas.moviedb.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qoolqas.moviedb.R
import com.qoolqas.moviedb.model.credits.CastItem
import kotlinx.android.synthetic.main.item_image_cast.view.*

class CreditAdapter(private val list: List<CastItem>) :
    RecyclerView.Adapter<CreditAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_cast, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if(list.size > 10){
            10
        } else {
            list.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.detail_cast_name.text = list[position].name
        Glide.with(holder.view)
            .load("https://image.tmdb.org/t/p/w185" + list[position].profilePath)
            .placeholder(R.color.gray)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.view.detail_cast_image)

//        holder.view.setOnClickListener {
//            val intentSimilar = Intent(holder.view.context, DetailActivity::class.java)
//            intentSimilar.putExtra(DetailActivity.EXTRA_ID, list[position].id)
//            holder.view.context.startActivity(intentSimilar)
//
//        }
    }
}