package com.solosol.solsolandroid.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.solsolandroid.databinding.ItemLastAccountBinding

class LastTransactionAdapter :
    ListAdapter<AccountItemData.PostItem, LastTransactionAdapter.ViewHolder>(diffUtil) {
    class ViewHolder(private val binding: ItemLastAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AccountItemData.PostItem) {
            itemView.run {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLastAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AccountItemData.PostItem>() {
            override fun areItemsTheSame(
                oldItem: AccountItemData.PostItem,
                newItem: AccountItemData.PostItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: AccountItemData.PostItem,
                newItem: AccountItemData.PostItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
