package com.moapp.letyouknowrecyclingapp


import com.google.gson.annotations.SerializedName

data class ResponseValidation(
    @SerializedName("result") val result: Boolean
){}