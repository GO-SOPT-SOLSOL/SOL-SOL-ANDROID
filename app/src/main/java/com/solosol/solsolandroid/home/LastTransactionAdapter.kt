package com.solosol.solsolandroid.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.solsolandroid.Bank
import com.solosol.solsolandroid.databinding.ItemLastAccountBinding
import com.solosol.solsolandroid.response.RecentTransferResponse

class LastTransactionAdapter :
    ListAdapter<RecentTransferResponse.Data.Transfer, LastTransactionAdapter.ViewHolder>(diffUtil) {
    class ViewHolder(private val binding: ItemLastAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecentTransferResponse.Data.Transfer) {
            itemView.run {
                with(binding) {
                    ivBankLogo.setImageResource(Bank.valueOf(item.bank).imageResourceId)
                    tvName.text = item.name
                    tvMoney.text = item.price.toString()
                }
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
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecentTransferResponse.Data.Transfer>() {
            override fun areItemsTheSame(
                oldItem: RecentTransferResponse.Data.Transfer,
                newItem: RecentTransferResponse.Data.Transfer
            ): Boolean {
                return oldItem.accountNumber == newItem.accountNumber
            }

            override fun areContentsTheSame(
                oldItem: RecentTransferResponse.Data.Transfer,
                newItem: RecentTransferResponse.Data.Transfer
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
