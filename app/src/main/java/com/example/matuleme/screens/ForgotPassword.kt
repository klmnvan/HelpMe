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
import com.example.matuleme.databinding.ActivitySignInBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.Requests
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
    }

    private fun pressBtns(){
        with(binding){
            btnSend.setOnClickListener {
                val email = inptEmail.text.toString()
                if(!email.isEmailValid()) {
                    Toast.makeText(this@ForgotPassword, "Email не валиден", Toast.LENGTH_SHORT).show()
                } else {
                    sendRequest()
                    /*val dialog = FragmentCheckEmail()
                    dialog.show(supportFragmentManager, "kldjsfhsdkl")
                   */

                    /*startActivity(Intent(this@SignIn, Home::class.java))
                    finish()*/
                }
            }
            btnBack.setOnClickListener {
                startActivity(Intent(this@ForgotPassword, SignIn::class.java))
                finish()
            }
        }
    }

    private fun sendRequest() {
        with(binding){
            email = inptEmail.text.toString()
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