package com.moapp.letyouknowrecyclingapp

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ClassificationRepository {
    private val api: Api
    private const val url = "http://34.64.115.128"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun postImg(bitmap: Bitmap?, context: Context) {
        val filePath = context.applicationInfo.dataDir + File.separator + System.currentTimeMillis() + ".jpg"
        val file = File(filePath)
        Log.d("lsh", "${filePath}, ${file.toString()}")
        //시작

        try {
            val outputStream = FileOutputStream(file)
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

            outputStream.close()
        } catch (err: IOException) {
            Log.d("lsh", "in try catch : ${err.toString()}")
        }

        val abFile = File(file.absolutePath)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), abFile)
        val body = MultipartBody.Part.createFormData("file", abFile.name, requestFile)

        api.postImg(body).enqueue(object : Callback<ResponseClassification> {
            override fun onResponse(
                call: Call<ResponseClassification>,
                response: Response<ResponseClassification>

            ) {
                //멈춰야함

                val body = response.body()
                if (response.isSuccessful && body != null) {
                    val result = body.result
                    Log.d("lsh", result)


                }
            }

            override fun onFailure(call: Call<ResponseClassification>, t: Throwable) {
                //멈춰야함

                Log.d("lsh", "${call.toString()}, ${t.toString()}")
            }
        })
    }
}