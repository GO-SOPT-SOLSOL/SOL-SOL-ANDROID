package com.solosol.solsolandroid.transfer1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.solsolandroid.Bank
import com.solosol.solsolandroid.databinding.ItemLastTransferBinding
import com.solosol.solsolandroid.response.RecentTransferResponse

class LastTransferListAdapter(private val onClickItem:(RecentTransferResponse.Data.Transfer) -> Unit): ListAdapter<RecentTransferResponse.Data.Transfer, LastTransferListAdapter.LastTransferViewHolder>(diffUtil) {

    class LastTransferViewHolder(
        private val binding: ItemLastTransferBinding,
        private val onClickItem: (RecentTransferResponse.Data.Transfer) -> Unit
    ):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecentTransferResponse.Data.Transfer) {
            itemView.run {
                binding.ivBankLogo.setImageResource(Bank.valueOf(item.bank).imageResourceId)
                binding.tvDate.text = item.createdAt
                binding.tvAccountNumber.text = item.accountNumber
                binding.tvUserName.text = item.name

                setOnClickListener {
                    onClickItem(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastTransferViewHolder {
        return LastTransferViewHolder(
            ItemLastTransferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickItem
        )
    }

    override fun onBindViewHolder(holder: LastTransferViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<RecentTransferResponse.Data.Transfer>() {
            override fun areItemsTheSame(
                oldItem: RecentTransferResponse.Data.Transfer,
                newItem: RecentTransferResponse.Data.Transfer
            ): Boolean {
                return oldItem.accountNumber== newItem.accountNumber
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