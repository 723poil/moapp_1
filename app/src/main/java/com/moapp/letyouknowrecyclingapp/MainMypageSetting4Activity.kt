package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting2Binding
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageSetting4Binding

class MainMypageSetting4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageSetting4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageSetting4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener(){
            finish()
        }
    }
}