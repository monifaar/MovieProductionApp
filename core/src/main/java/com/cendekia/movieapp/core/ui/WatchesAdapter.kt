package com.cendekia.movieapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cendekia.movieapp.core.R
import com.cendekia.movieapp.core.databinding.ItemListWatchesBinding
import com.cendekia.movieapp.core.domain.model.Watches
import java.util.*

class WatchesAdapter : RecyclerView.Adapter<WatchesAdapter.ListViewHolder>() {

    private var listData = ArrayList<Watches>()
    var onItemClick: ((Watches) -> Unit)? = null

    fun setData(newListData: List<Watches>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_watches, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListWatchesBinding.bind(itemView)
        private val links = "https://image.tmdb.org/t/p/w500"

        fun bind(data: Watches) {

            val poster = "$links/${data.poster_path}"

            with(binding) {
                Glide.with(itemView.context)
                    .load(poster)
                    .into(ivItemPoster)

                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.release_data
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}