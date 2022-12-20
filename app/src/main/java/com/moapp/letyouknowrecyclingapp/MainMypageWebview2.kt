package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageWebview1Binding
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageWebview2Binding
import kotlinx.android.synthetic.main.activity_main_mypage_webview1.*

class MainMypageWebview2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageWebview2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageWebview2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbutton.setOnClickListener(){
            onBackPressed()
        }
        binding.webView.webViewClient
        webView.loadUrl("https://www.dgs.go.kr/dgs/minwon/page.php?mnu_uid=11027")
    }
}