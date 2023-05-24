package com.solosol.solsolandroid.transfer1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.solsolandroid.databinding.ItemMyAccountListBinding
import com.solosol.solsolandroid.transfer1.model.MyAccountResponse

class MyAccountAdapter : ListAdapter<MyAccountResponse.Data, MyAccountAdapter.ViewHolder>(diffUtil) {

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
        fun bind(item: MyAccountResponse.Data) {
            itemView.run {
                binding.tvBankName.text = item.bank
                binding.tvAccountNumber.text = item.accountNumber
            }
        }
    }

    companion object {
        val diffUtil = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<MyAccountResponse.Data>() {
            override fun areItemsTheSame(
                oldItem: MyAccountResponse.Data,
                newItem: MyAccountResponse.Data
            ): Boolean {
                return oldItem.accountNumber == newItem.accountNumber
            }

            override fun areContentsTheSame(
                oldItem: MyAccountResponse.Data,
                newItem: MyAccountResponse.Data
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
