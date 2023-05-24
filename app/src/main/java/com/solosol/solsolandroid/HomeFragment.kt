package com.solosol.solsolandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solosol.solsolandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewPagerAdapter = HomeViewPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewpager()
    }

    private fun initViewpager() {
        binding.vpMyAccount.adapter = homeViewPagerAdapter
        homeViewPagerAdapter.submitList(
            listOf(
                AccountItemData(
                    "1234-1234-1234", "우리은행", "1000000", listOf(
                        AccountItemData.PostItem(
                            "우리은행", "김솔", "1000000"
                        ),
                        AccountItemData.PostItem(
                            "우리은행", "김솔", "1000000"
                        )
                    )
                ),AccountItemData(
                    "1234-1234-1234", "우리은행", "1000000", listOf(
                        AccountItemData.PostItem(
                            "우리은행", "김솔", "1000000"
                        )
                    )
                )
            )
        )
    }


}