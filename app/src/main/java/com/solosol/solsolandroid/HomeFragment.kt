package com.solosol.solsolandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.solosol.solsolandroid.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

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
        initData()

        initViewpager()

    }

    private fun initData() {
        lifecycleScope.launch {
            val response = ApiFactory.solService.getBannerInfo()
            if (response.isSuccessful) {
                val bannerInfo = response.body()?.data?.first()
                binding.run {
                    tvBannerTitle.text = bannerInfo?.title.orEmpty()
                    tvBannerContent.text = bannerInfo?.content.orEmpty()
                }
            }
        }
    }

    private fun initViewpager() {
        binding.vpMyAccount.adapter = homeViewPagerAdapter
        homeViewPagerAdapter.submitList(
            listOf()
        )
    }


}