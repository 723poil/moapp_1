package com.moapp.letyouknowrecyclingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_tab.view.*
import kotlinx.android.synthetic.main.header_nav.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //드로어
    lateinit var toggle: ActionBarDrawerToggle
    private var data1 = 1
    var backKeyPressedTime : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //툴바
        setSupportActionBar(binding.toolbar)

        //시작이 HomeFragment로 화면이 대체됨
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MainHomeFragment()).commit()

        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, 0, 0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        //기본타이틀 안나오게
        supportActionBar?.setDisplayShowTitleEnabled(false)



        //드로어 아이템 선택 리스너
        binding.drawerView.setNavigationItemSelectedListener() {
            when(it.itemId) {


                R.id.item1 -> {
                    var intent = Intent(this, MainMypageSetting2Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item2 -> {
                    var intent = Intent(this, MainMypageSetting3Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item3 -> {
                    var intent = Intent(this, MainMypageSetting4Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item4 -> {
                    var intent = Intent(this, MainMypageSetting5Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }

                R.id.item5 -> {
                    var intent = Intent(this, MainMypageSetting6Activity::class.java)
                    startActivity(intent)
                    binding.drawer.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }


            }
            return@setNavigationItemSelectedListener true
        }

        //바텀바 아이템 선택 리스터 (Fragment)
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.search -> if(data1 != 1){
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MainSearchFragment()).addToBackStack(null).commit()
                    data1 = 1
                }
                //R.id.search -> replaceFragment(MainSearchFragment())
                R.id.home -> if(data1 != 0){
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MainHomeFragment()).addToBackStack(null).commit()
                    data1 = 0
                }
                //R.id.home -> replaceFragment(MainHomeFragment())
                R.id.mypage -> if(data1 != 2){
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MainMypageFragment()).addToBackStack(null).commit()
                    data1 = 2
                }
                //R.id.mypage -> replaceFragment(MainMypageFragment())


                else -> {

                }

            }
            true
        }

        binding.bottomNavView.selectedItemId = R.id.home

    }

    override fun onBackPressed() {

        if (System.currentTimeMillis() > backKeyPressedTime + 1500) {
            backKeyPressedTime = System.currentTimeMillis()
            binding.bottomNavView.selectedItemId = R.id.home
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MainHomeFragment()).commit()
            Toast.makeText(this, "한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()

            return
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 1500) {
            finishAffinity()
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}