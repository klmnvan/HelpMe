package com.example.matuleme.objects

import android.text.TextUtils

object General {

    /** Функция, которая проверяет соответствие приходящей строки паттерну электронной почты */
    fun String.isEmailValid () : Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

}