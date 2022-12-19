package com.moapp.letyouknowrecyclingapp

import android.view.View

interface Interaction: View.OnClickListener {
    fun onBannerItemClicked(bannerItem: BannerItem)
}