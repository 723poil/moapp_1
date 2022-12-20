package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting2Binding
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting6Binding

class MainMypageSetting6Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageSetting6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageSetting6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }
    }
}