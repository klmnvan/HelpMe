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
        getCross()
    }

    private fun getCross() {
        ProductsStorage.listProduct.clear()
        ProductsStorage.listProduct.add(ShopModelTest(true, "Тестовый заголовок 1", "Какое-то описание",500, "Tennis"))
        ProductsStorage.listProduct.add(ShopModelTest(false, "Тестовый 2",  "Какое-то описание",600, "Tennis"))
        ProductsStorage.listProduct.add(ShopModelTest(true, "заголовок 3",  "Какое-то опsdfgисание",700, "Outdoor"))
        ProductsStorage.listProduct.add(ShopModelTest(true, "заголsdfовок 4",  "Какsdfое-то описание",800, "Tennis"))
    }

}