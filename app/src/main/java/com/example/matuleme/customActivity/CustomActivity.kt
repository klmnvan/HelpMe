package com.example.matuleme.customActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matuleme.R
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.UserData

open class CustomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefManager.init(this@CustomActivity)
        getCross()
    }

    private fun getCross() {
        UserData.listCross.add(ShopModelTest(true, "Тестовый заголовок 1",
            500))
        UserData.listCross.add(ShopModelTest(false, "Тестовый 2", 600))
        UserData.listCross.add(ShopModelTest(true, "заголовок 3", 700))
        UserData.listCross.add(ShopModelTest(true, "заголовок 4", 800))
    }

}