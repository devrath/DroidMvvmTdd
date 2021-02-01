package com.demo.code.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.code.R
import com.demo.code.models.PlaylistItem
import kotlinx.android.synthetic.main.playlist_item.view.*

class MyPlaylistRecyclerViewAdapter() : RecyclerView.Adapter<ViewHolder>() {

    private var values = ArrayList<PlaylistItem>()

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.playlist_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.playlist_name?.text = values[position].name
        holder.playlist_category?.text = values[position].category


    }

    public fun updateList(listItems: List<PlaylistItem>) {
        values.clear()
        values.addAll(listItems)
        notifyDataSetChanged()
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val playlist_name = view.playlist_name
    val playlist_category = view.playlist_category
}