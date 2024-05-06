package com.example.matuleme.objects

import android.content.Context
import android.content.SharedPreferences

object PrefManager {
    private lateinit var actSystem: SharedPreferences

    fun init(context: Context){
        actSystem = context.getSharedPreferences("Actsystem", Context.MODE_PRIVATE)
    }

    var act: Int
        get() = actSystem.getInt("act", 0)
        set(value) = actSystem.edit().putInt("act", value).apply()

}