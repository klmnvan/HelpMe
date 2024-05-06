package com.example.matuleme.customActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matuleme.R
import com.example.matuleme.objects.PrefManager

open class CustomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefManager.init(this@CustomActivity)
    }

}