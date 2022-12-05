package com.example.mvp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp.databinding.ItemNotifBinding

class AdaperNotif (private val data : MutableList<ItemNotifModel> = mutableListOf()): RecyclerView.Adapter<AdaperNotif.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotifBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding:ItemNotifBinding ): RecyclerView.ViewHolder(binding.root){
        fun bindItem(item: ItemNotifModel){
            binding.tvTitle.text = item.title
            binding.tvAccountInfor.text = item.accountDetail

            binding.tvAmount.text = item.transactionAmount


            /*val iconResource =  when(item.type) {
                "TIPE TOPUP" -> R.drawable.icon_1
                "Transfer Masuk" -> R.drawable.icon_2
                "Pembelian" -> R.drawable.icon_3
                else -> R.drawable.img_3
            }
            binding.ivTransaction.setImageResource(iconResource)*/
            val iconBackround =  when(item.type) {
                "TIPE TOPUP" -> R.drawable.icon_1
                "Transfer Masuk" -> R.drawable.icon_2
                "Pembelian" -> R.drawable.icon_3
                else -> R.color.white
            }
            binding.ivTransaction.setBackgroundResource(iconBackround)


            when(item.type) {
                "Transfer Masuk" -> binding.tvAmount.setTextColor(Color.GREEN)
                else -> binding.tvAmount.setTextColor(Color.RED) }



        }
    }
}