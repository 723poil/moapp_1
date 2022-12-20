package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting1Binding
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting2Binding

class MainMypageSetting2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageSetting2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageSetting2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }
    }
}