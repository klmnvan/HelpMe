package com.example.matuleme.customActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matuleme.R
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.ProductsStorage
import com.example.matuleme.objects.UserData

open class CustomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefManager.init(this@CustomActivity)
    }

}