package com.moapp.letyouknowrecyclingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.moapp.letyouknowrecyclingapp.databinding.ActivityMainMypageWebview1Binding

class MainMypageWebview1 : AppCompatActivity() {
    private lateinit var binding: ActivityMainMypageWebview1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMypageWebview1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbutton.setOnClickListener(){
            onBackPressed()
        }

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.knu.ac.kr")
    }
}