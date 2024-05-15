package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.databinding.ActivityForgotPasswordBinding
import com.example.matuleme.databinding.ActivityNewPasswordBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.Requests
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.random.Random

class NewPassword : AppCompatActivity() {
    private lateinit var binding: ActivityNewPasswordBinding
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
    }

    private fun pressBtns(){
        with(binding){
            btnGenerate.setOnClickListener {
                if(inptPhraze.text.length >= 8) {
                    generatePass(inptPhraze.text.toString())
                } else {
                    Toast.makeText(this@NewPassword, "Фраза слишком короткая", Toast.LENGTH_SHORT).show()
                }
            }
            btnBack.setOnClickListener {
                startActivity(Intent(this@NewPassword, SignIn::class.java))
                finish()
            }
            btnNewPass.setOnClickListener {
                sendRequest()
            }
        }
    }

    private fun rand(str1: String, str2: String): String {
        val rand = Random.nextBoolean()
        return if (rand) str1
        else str2
    }

    private fun generatePass(str: String): String {
        var newStr = ""
        str.forEach { c ->
            newStr += when(c) {
                'а' -> rand("4", "f")
                'б' -> rand("6", rand("B", "b"))
                'в' -> rand("4", "f")
                'г' -> rand("4", "f")
                'д' -> rand("4", "f")
                'е' -> rand("4", "f")
                'ё' -> rand("4", "f")
                'ж' -> rand("4", "f")
                'з' -> rand("4", "f")
                'и' -> rand("1", rand("i", "I"))
                'й' -> rand("4", "f")
                'к' -> rand("4", "f")
                'л' -> rand("4", "f")
                'м' -> rand("4", "f")
                'о' -> rand("0", rand("()", "O"))
                'п' -> rand("4", "f")
                'р' -> rand("4", "f")
                'с' -> rand("(", "C")
                'т' -> rand("4", "f")
                'у' -> rand("4", "f")
                'ф' -> rand("4", "f")
                'х' -> rand("4", "f")
                'ц' -> rand("4", "f")
                'ч' -> rand("4", "f")
                'ш' -> rand("4", "f")
                'щ' -> rand("4", "f")
                'ь' -> rand("4", "f")
                'ы' -> rand("4", "f")
                'ъ' -> rand("4", "f")
                'э' -> rand("4", "f")
                'ю' -> rand("4", "f")
                'я' -> rand("4", "f")
                else -> {}
            }
        }
        return newStr
    }

    private fun sendRequest() {
        with(binding){
            password = inptPassword.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Requests.sendOtp(email)
                    runOnUiThread {
                        val frg = FragmentCheckEmail()
                        frg.show(supportFragmentManager, "send otp")
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@ForgotPassword, e.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("error send otp", e.message.toString())
                }
            }
        }
    }
}