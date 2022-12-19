package com.moapp.letyouknowrecyclingapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.viewpager2.widget.ViewPager2
import com.moapp.letyouknowrecyclingapp.databinding.FragmentMainHomeBinding
import kotlinx.android.synthetic.main.fragment_main_home.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainHomeFragment : Fragment(), View.OnClickListener, Interaction  {
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewModel: MainHomeFragmentViewModel
    private var isRunning = true
    private lateinit var binding: FragmentMainHomeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //fragment에서는 구성하는 레이아웃을 사용할 수 있는 시기가 onViewCreated 이후부터이다.

        binding.cameraBtn1.setOnClickListener{
            var intent = Intent(context, ImageActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn1.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn2.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn3.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn4.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn5.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn6.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn7.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn8.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }
        binding.menuBtn9.setOnClickListener{
            var intent = Intent(context, TabActivity::class.java)
            startActivity(intent)
        }



        viewModel = ViewModelProvider(this).get(MainHomeFragmentViewModel::class.java)
        viewModel.setBannerItems(
            listOf(
                BannerItem(R.drawable.first),
                BannerItem(R.drawable.second),
                BannerItem(R.drawable.third),
                BannerItem(R.drawable.fourth),
                BannerItem(R.drawable.fifth)
            )
        )

        //iv_hamburger.setOnClickListener(this)
        initViewPager2()
        subscribeObservers()
        autoScrollViewPager()
    }

    private fun initViewPager2() {
        viewPager2.apply {
            viewPagerAdapter = ViewPagerAdapter(this@MainHomeFragment)
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    isRunning=true
                    banner_page_number.text = "${position + 1}"

                    //직접 유저가 스크롤했을 떄!
                    viewModel.setCurrentPosition(position)
                }
            })
        }
    }

    private fun subscribeObservers() {
        viewModel.bannerItemList.observe(viewLifecycleOwner, Observer { bannerItemList ->
            viewPagerAdapter.submitList(bannerItemList)
        })
        viewModel.currentPosition.observe(viewLifecycleOwner, Observer { currentPosition ->
            viewPager2.currentItem = currentPosition
        })
    }

    private fun autoScrollViewPager() {
        lifecycleScope.launch {
            whenResumed {
                while (isRunning) {
                    delay(5000)
                    viewModel.getcurrentPosition()?.let {
                        viewModel.setCurrentPosition((it.plus(1)) % 5)
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
    }

    override fun onBannerItemClicked(bannerItem: BannerItem) {
        startActivity(Intent(context, EventActivity::class.java))
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                //R.id.iv_hamburger -> {

                //}
            }
        }
    }
}