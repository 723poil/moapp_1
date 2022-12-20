package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting2Binding
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting5Binding

class MainMypageSetting5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageSetting5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageSetting5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }
    }
}