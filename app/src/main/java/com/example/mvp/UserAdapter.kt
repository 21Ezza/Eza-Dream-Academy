package com.example.okhttp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvp.data.model.User
import com.example.mvp.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var cardClickListener: ((firstName : String, lastName : String, email: String, avatar: String) -> Unit)? = null
    private val data = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], cardClickListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<User>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: User, listener: ((String, String, String, String) -> Unit)?) {
            with(binding) {
                tvName.text = "${item.firstName} ${item.lastName}"
                tvThumbnail.text = item.email
                /*tvEmail.text = item.email*/
                Glide
                    .with(root.context)
                    .load(item.avatar)
                    .into(ivAvatar)
                root.setOnClickListener {
                    listener?.invoke(item.firstName, item.lastName, item.email, item.avatar)
                }
            }
        }
    }
    fun setOnCardClickListener(listener: (String, String, String, String) -> Unit){
        this.cardClickListener = listener
    }
}