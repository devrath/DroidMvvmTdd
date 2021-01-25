package com.demo.code.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.demo.code.R
import com.demo.code.databinding.PlaylistItemBinding
import com.demo.code.models.PlaylistDataItem
import kotlinx.android.synthetic.main.playlist_item.view.*

class MyPlaylistRecyclerViewAdapter(
    private val values: ArrayList<PlaylistDataItem>
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.playlist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.playlist_name?.text = values.get(position).name
        holder.playlist_category?.text = values.get(position).category
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val playlist_name = view.playlist_name
    val playlist_category = view.playlist_category
}