package com.solosol.solsolandroid.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.solsolandroid.Bank
import com.solosol.solsolandroid.databinding.ItemAccountBinding
import com.solosol.solsolandroid.response.MyAccountListResponse
import java.text.DecimalFormat

class HomeViewPagerAdapter(private val transferOnClick: (MyAccountListResponse.Data) -> Unit) :
    ListAdapter<MyAccountListResponse.Data, HomeViewPagerAdapter.AccountViewHolder>(diffUtil) {

    class AccountViewHolder(
        private val binding: ItemAccountBinding,
        private val transferOnClick: (MyAccountListResponse.Data) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val lastTransactionAdapter = LastTransactionAdapter()
        fun bind(item: MyAccountListResponse.Data) {
            itemView.run {
                with(binding) {
                    val dec = DecimalFormat("#,###")
                    tvPageTitle.text = item.name
                    tvAccountNumber.text = item.accountNumber
                    tvMoney.text = "${dec.format(item.balance)}원"
                    val imageResource = Bank.valueOf(item.bank.toString()).imageResourceId
                    ivBankLogo.setImageResource(imageResource)
                }
                binding.rvLastTransaction.adapter = lastTransactionAdapter
//                lastTransactionAdapter.submitList(item)
                setOnClickListener {
                    transferOnClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ItemAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), transferOnClick
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyAccountListResponse.Data>() {
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

