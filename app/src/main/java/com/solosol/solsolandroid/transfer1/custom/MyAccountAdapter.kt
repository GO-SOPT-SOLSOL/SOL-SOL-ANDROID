package com.solosol.solsolandroid.transfer1.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.solsolandroid.Bank
import com.solosol.solsolandroid.databinding.ItemMyAccountListBinding
import com.solosol.solsolandroid.response.MyAccountListResponse

class MyAccountAdapter : ListAdapter<MyAccountListResponse.Data, MyAccountAdapter.ViewHolder>(
    diffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMyAccountListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ViewHolder(private val binding: ItemMyAccountListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyAccountListResponse.Data) {
            itemView.run {
                item.bank?.let {
                    binding.ivBankLogo.setImageResource(Bank.valueOf(it).imageResourceId)
                }
                binding.tvBankName.text = item.name
                binding.tvAccountNumber.text = item.accountNumber
            }
        }
    }

    companion object {
        val diffUtil = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<MyAccountListResponse.Data>() {
            override fun areItemsTheSame(
                oldItem: MyAccountListResponse.Data,
                newItem: MyAccountListResponse.Data
            ): Boolean {
                return oldItem.accountNumber == newItem.accountNumber
            }

            override fun areContentsTheSame(
                oldItem: MyAccountListResponse.Data,
                newItem: MyAccountListResponse.Data
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
