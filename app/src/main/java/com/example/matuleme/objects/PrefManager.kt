package com.example.matuleme.objects

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.matuleme.models.ShopModelTest
import io.paperdb.Paper

object PrefManager {
    private lateinit var actSystem: SharedPreferences

    fun init(context: Context) {
        actSystem = context.getSharedPreferences("Actsystem", Context.MODE_PRIVATE)
        Paper.init(context)
    }

    var act: Int
        get() = actSystem.getInt("act", 0)
        set(value) = actSystem.edit().putInt("act", value).apply()

    var listProductFav: MutableList<ShopModelTest>
        get() = Paper.book().read("favProduct", ArrayList())!!
        set(value) {
            Paper.book().write("favProduct", value)
        }


}