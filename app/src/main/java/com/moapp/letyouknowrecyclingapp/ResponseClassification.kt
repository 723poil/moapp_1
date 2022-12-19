package com.moapp.letyouknowrecyclingapp

import com.google.gson.annotations.SerializedName

data class ResponseClassification(
    @SerializedName("originalname") val originalname: String,
    @SerializedName("fileName") val fileName: String,
    @SerializedName("size") val size: Int,
    @SerializedName("status") val status: Boolean,
    @SerializedName("result") val result: String
){}
