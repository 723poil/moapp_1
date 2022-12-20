package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting2Binding
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting3Binding

class MainMypageSetting3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageSetting3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageSetting3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }
    }
}