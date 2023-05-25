package com.solosol.solsolandroid.home

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.solosol.solsolandroid.R
import com.solosol.solsolandroid.ServicePool
import com.solosol.solsolandroid.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewPagerAdapter = HomeViewPagerAdapter {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBannerData()
        initViewpager()
        initAccountData()
    }

    private fun initAccountData() {
        lifecycleScope.launch {
            val response = ServicePool.solService.getAccountListInfo()
            if (response.isSuccessful) {
                val accountList = response.body()?.data
                homeViewPagerAdapter.submitList(accountList)
            }
        }
    }

    private fun initBannerData() {
        lifecycleScope.launch {
            val response = ServicePool.solService.getBannerInfo()
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

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }

        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )

        with(binding) {
            vpMyAccount.adapter = homeViewPagerAdapter
            vpMyAccount.offscreenPageLimit = 1
            vpMyAccount.setPageTransformer(pageTransformer)
            vpMyAccount.addItemDecoration(itemDecoration)
            indicator.attachTo(vpMyAccount)
        }
    }

    class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
        RecyclerView.ItemDecoration() {

        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMarginInDp).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }

    }
}