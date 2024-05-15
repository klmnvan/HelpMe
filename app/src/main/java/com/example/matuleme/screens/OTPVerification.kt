package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.matuleme.R
import com.example.matuleme.databinding.ActivityForgotPasswordBinding
import com.example.matuleme.databinding.ActivityOtpverificationBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.Requests
import com.example.matuleme.objects.UserStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class OTPVerification : AppCompatActivity() {
    private lateinit var binding: ActivityOtpverificationBinding
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        listenFields()
        startTimer()
    }

    private fun startTimer(){
        val timer = object : CountDownTimer(60000, 1000) {

            override fun onTick(p0: Long) {
                with(binding) {
                    txtTimer.text = "00:${p0/1000}"
                }
            }

            override fun onFinish() {
                //Скрывать и показывать кнопки какие надо будет
                startTimer()
            }
        }.start()
    }

    private fun listenFields() {
        with(binding){
            tf1.addTextChangedListener {
                customizeFields()
                tf2.requestFocus()
            }
            tf2.addTextChangedListener {
                customizeFields()
                tf3.requestFocus()
            }
            tf3.addTextChangedListener {
                customizeFields()
                tf4.requestFocus()
            }
            tf4.addTextChangedListener {
                customizeFields()
                tf5.requestFocus()
            }
            tf5.addTextChangedListener {
                customizeFields()
                tf6.requestFocus()
            }
            tf6.addTextChangedListener {
                customizeFields()
            }
        }
    }

    private fun customizeFields() {
        with(binding) {
            if(tf1.text.isNotEmpty()) { tf1.background = getDrawable(R.drawable.shape_white_10rad_blue_corners)
            } else {
                tf1.background = getDrawable(R.drawable.shape_white_10rad)
            }
            if(tf2.text.isNotEmpty()) { tf2.background = getDrawable(R.drawable.shape_white_10rad_blue_corners)
            } else {
                tf2.background = getDrawable(R.drawable.shape_white_10rad)
            }
            if(tf3.text.isNotEmpty()) { tf3.background = getDrawable(R.drawable.shape_white_10rad_blue_corners)
            } else {
                tf3.background = getDrawable(R.drawable.shape_white_10rad)
            }
            if(tf4.text.isNotEmpty()) { tf4.background = getDrawable(R.drawable.shape_white_10rad_blue_corners)
            } else {
                tf4.background = getDrawable(R.drawable.shape_white_10rad)
            }
            if(tf5.text.isNotEmpty()) { tf5.background = getDrawable(R.drawable.shape_white_10rad_blue_corners)
            } else {
                tf5.background = getDrawable(R.drawable.shape_white_10rad)
            }
            if(tf6.text.isNotEmpty()) { tf6.background = getDrawable(R.drawable.shape_white_10rad_blue_corners)
            } else {
                tf6.background = getDrawable(R.drawable.shape_white_10rad)
            }
            if( tf1.text.isNotEmpty() && tf2.text.isNotEmpty() && tf3.text.isNotEmpty() && tf4.text.isNotEmpty() && tf5.text.isNotEmpty() && tf6.text.isNotEmpty()) {
                sendRequest()
            }
        }
    }

    private fun pressBtns(){
        with(binding){
            btnBack.setOnClickListener {
                startActivity(Intent(this@OTPVerification, SignIn::class.java))
                finish()
            }
        }
    }

    private fun sendRequest() {
        with(binding){
            token = "${tf1.text}${tf2.text}${tf3.text}${tf4.text}${tf5.text}${tf6.text}"
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Requests.checkToken("nesklmnvan@gmail.com", token)
                    runOnUiThread {
                        startActivity(Intent(this@OTPVerification, NewPassword::class.java))
                        finish()
                    }
                } catch (e: Exception) {
                    customizeFieldsError()
                    Toast.makeText(this@OTPVerification, e.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("error checkToken", e.message.toString())
                }
            }
        }
    }

    private fun customizeFieldsError() {
        with(binding) {
            tf1.text.clear()
            tf2.text.clear()
            tf3.text.clear()
            tf4.text.clear()
            tf5.text.clear()
            tf6.text.clear()
            tf1.requestFocus()
            tf1.background = getDrawable(R.drawable.shape_white_10rad_red_corners)
            tf2.background = getDrawable(R.drawable.shape_white_10rad_red_corners)
            tf3.background = getDrawable(R.drawable.shape_white_10rad_red_corners)
            tf4.background = getDrawable(R.drawable.shape_white_10rad_red_corners)
            tf5.background = getDrawable(R.drawable.shape_white_10rad_red_corners)
            tf6.background = getDrawable(R.drawable.shape_white_10rad_red_corners)
        }
    }
}