package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivitySplashBinding
import com.example.matuleme.objects.PrefManager
import java.util.concurrent.TimeUnit

class Splash : CustomActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    TimeUnit.SECONDS.sleep(2)
                    if(PrefManager.act == 0){
                        startActivity(Intent(this@Splash, OnBoard::class.java))
                        finish()
                    }
                    if(PrefManager.act == 1){
                        startActivity(Intent(this@Splash, SignIn::class.java))
                        finish()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }

}