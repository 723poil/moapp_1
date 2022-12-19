package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting1Binding

class MainMypageSetting1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageSetting1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageSetting1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }
    }
}