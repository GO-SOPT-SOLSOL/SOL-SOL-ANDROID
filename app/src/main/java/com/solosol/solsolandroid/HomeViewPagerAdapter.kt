package com.solosol.solsolandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.solsolandroid.databinding.ItemAccountBinding

class HomeViewPagerAdapter :
    ListAdapter<AccountItemData, HomeViewPagerAdapter.AccountViewHolder>(diffUtil) {

    class AccountViewHolder(private val binding: ItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val lastTransactionAdapter = LastTransactionAdapter()
        fun bind(item: AccountItemData) {
            itemView.run {
                binding.rvLastTransaction.adapter = lastTransactionAdapter

                lastTransactionAdapter.submitList(item.lastPostList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ItemAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AccountItemData>() {
            override fun areItemsTheSame(
                oldItem: AccountItemData,
                newItem: AccountItemData
            ): Boolean {
                return oldItem.accountNumber == newItem.accountNumber
            }

            override fun areContentsTheSame(
                oldItem: AccountItemData,
                newItem: AccountItemData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

