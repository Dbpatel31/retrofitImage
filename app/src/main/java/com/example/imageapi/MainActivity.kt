package com.example.imageapi

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        imageView= findViewById(R.id.imageView)

        var responce= RetrofitInstance.api.getImageInfo()

        responce.enqueue(object :  retrofit2.Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                if(response.isSuccessful){
                    val imageUrl= response.body()?.download_url
                    Glide.with(this@MainActivity).load(imageUrl).into(imageView)
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        }
        )
    }
}


