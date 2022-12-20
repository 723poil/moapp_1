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
        val data = intent.getIntExtra("data2", 0)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, 0, 0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        //기본타이틀 안나오게
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //탭바 바인딩
        val tabLayout: TabLayout = binding.tabs


        val tabTitleArray = arrayOf(
            "알루미늄",
            "종이팩",
            "유리",
            "플라스틱",
            "의류",
            "종이류",
            "목재",
            "음식물",
        )

        //viewpager2 바인딩하고, 어뎁터연결
        val viewPager = binding.viewpager2
        viewPager.adapter = TabFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()


        if(data == 1){
            binding.viewpager2.setCurrentItem(0,false)
        }
        else if(data == 2){
            binding.viewpager2.setCurrentItem(1,false)
        }
        else if(data == 3){
            binding.viewpager2.setCurrentItem(2,false)
        }
        else if(data == 4){
            binding.viewpager2.setCurrentItem(3,false)
        }
        else if(data == 5){
            binding.viewpager2.setCurrentItem(4,false)
        }
        else if(data == 6){
            binding.viewpager2.setCurrentItem(5,false)
        }
        else if(data == 7){
            binding.viewpager2.setCurrentItem(6,false)
        }
        else if(data == 8){
            binding.viewpager2.setCurrentItem(7,false)
        }


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

        binding.home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

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