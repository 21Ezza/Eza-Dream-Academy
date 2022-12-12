package com.example.mvp.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvp.databinding.ItemUserBinding
import com.example.mvp.network.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val data = mutableListOf<Movie>()
    private var _listener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], _listener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<Movie>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Movie, listener: ((Movie) -> Unit)?) {
            with(binding) {
                tvName.text = item.userId.toString()
                tvThumbnail.text = item.title
                tvEmail.text = item.body


                root.setOnClickListener {
                    listener?.invoke(item)
                }
            }
        }
    }

    fun setListener(listener: (Movie) -> Unit) {
        this._listener = listener
    }
}