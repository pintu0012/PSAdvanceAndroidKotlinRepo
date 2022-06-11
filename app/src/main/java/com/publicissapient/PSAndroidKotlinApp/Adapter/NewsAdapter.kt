package com.publicissapient.PSAndroidKotlinApp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.publicissapient.PSAndroidKotlinApp.Model.NewsModelClass
import com.publicissapient.PSAndroidKotlinApp.R
import com.publicissapient.PSAndroidKotlinApp.databinding.NewItemBinding

class NewsAdapter (private val mList: List<NewsModelClass>): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    var news = mutableListOf<NewsModelClass>()
    fun setMovieList(movies: List<NewsModelClass>) {
        this.news = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemModel = mList[position]
        Glide.with(holder.itemView.context)
            .load(ItemModel.imageUrl) // image url
//            .placeholder(R.drawable.placeholder) // any placeholder to load at start
//            .error(R.drawable.imagenotfound)  // any image in case of error
            .override(200, 200) // resizing
            .centerCrop()
            .into(holder.imageView)
        holder.title.setText(ItemModel.title)
        holder.subtitle.setText(ItemModel.content)

    }

    class ViewHolder(private val itemBinding:  NewItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        val imageView :ImageView = itemView.findViewById(R.id.imageView)
        val title :TextView = itemView.findViewById(R.id.title)
        val subtitle :TextView = itemView.findViewById(R.id.subtitle)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}