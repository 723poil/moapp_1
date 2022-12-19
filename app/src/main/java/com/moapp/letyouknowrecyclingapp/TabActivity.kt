package com.moapp.letyouknowrecyclingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainBinding
import com.moapp.letyouknowrecyclingapp.databinding.ActivityTabBinding

class TabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, 0, 0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        //탭바 바인딩
        val tabLayout: TabLayout = binding.tabs

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab4)

        val tab5: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab5)

        val tab6: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab6)

        val tab7: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab7)

        val tab8: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab8)

        val tab9: TabLayout.Tab = tabLayout.newTab()
        tabLayout.addTab(tab9)


        //viewpager2 바인딩하고, 어뎁터연결
        val viewPager = binding.viewpager2
        viewPager.adapter = TabFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            // 탭 버튼을 선택할 때 이벤트
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            //선택된 탭 버튼을 다시 선택할 때 이벤트
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            //다른 탭 버튼을 눌러 선택된 탭 버튼이 해제될 때 이벤트
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        //플롯팅액션바
        binding.FAB.setOnClickListener() {
            var intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
        }


        //드로어 아이템 선택 리스너
        binding.drawerView.setNavigationItemSelectedListener() {
            when(it.itemId) {
                R.id.item1 -> {
                    var intent = Intent(this, MainMypageSetting1Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item2 -> {
                    var intent = Intent(this, MainMypageSetting1Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item3 -> {
                    var intent = Intent(this, MainMypageSetting1Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item4 -> {
                    var intent = Intent(this, MainMypageSetting1Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item5 -> {
                    var intent = Intent(this, MainMypageSetting1Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }


            }
            return@setNavigationItemSelectedListener true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}