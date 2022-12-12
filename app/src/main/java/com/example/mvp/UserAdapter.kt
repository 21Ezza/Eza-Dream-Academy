package com.example.mvp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvp.databinding.ItemUserBinding
import com.example.mvp.network.model.MakeUpItem

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val data = mutableListOf<MakeUpItem>()
    private var _listener: ((MakeUpItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], _listener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<MakeUpItem>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: MakeUpItem, listener: ((MakeUpItem) -> Unit)?) {
            with(binding) {
                tvName.text = item.name?.trim()
                tvThumbnail.text = item.brand
                tvEmail.text = "${item.priceSign} ${item.price}"
                item.imageLink?.let { img ->
                    Glide
                        .with(root.context)
                        .load(img)
                        .into(ivAvatar)
                }

                root.setOnClickListener {
                    listener?.invoke(item)
                }
            }
        }
    }

    fun setListener(listener: (MakeUpItem) -> Unit) {
        this._listener = listener
    }
}