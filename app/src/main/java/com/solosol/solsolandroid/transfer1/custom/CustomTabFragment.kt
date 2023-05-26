package com.solosol.solsolandroid.transfer1.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.solosol.solsolandroid.ServicePool
import com.solosol.solsolandroid.databinding.FragmentCustomBinding
import kotlinx.coroutines.launch

class CustomTabFragment() : Fragment() {

    private var _binding: FragmentCustomBinding? = null
    private val binding get() = _binding!!

    private var myAccountAdapter: MyAccountAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCustomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        fetchData()
    }

    override fun onDestroyView() {
        _binding = null
        myAccountAdapter = null
        super.onDestroyView()
    }

    private fun initView() {
        myAccountAdapter = MyAccountAdapter()
        binding.rvMyAccount.apply {
            adapter = myAccountAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            val response = ServicePool.solService.getAccountListInfo()
            if (response.isSuccessful) {
                val accountList = response.body()?.data
                myAccountAdapter?.submitList(accountList)
            }
        }
    }
}
