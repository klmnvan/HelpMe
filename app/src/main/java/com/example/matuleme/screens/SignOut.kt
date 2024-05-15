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
import com.example.matuleme.databinding.ActivitySignInBinding
import com.example.matuleme.databinding.ActivitySignOutBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.Client
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.Requests
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SignOut : AppCompatActivity() {
    private lateinit var binding: ActivitySignOutBinding
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
    }

    private fun pressBtns(){
        with(binding){
            btnBack.setOnClickListener {
                startActivity(Intent(this@SignOut, SignIn::class.java))
                finish()
            }
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@SignOut, SignIn::class.java))
                finish()
            }
            btnSignOut.setOnClickListener {
                val email = inptEmail.text.toString()
                if (!email.isEmailValid()) {
                    Toast.makeText(this@SignOut, "Email не валиден", Toast.LENGTH_SHORT).show()
                } else {
                    sendRequest()
                }
            }
        }

    }

    private fun sendRequest() {
        with(binding){
            email = inptEmail.text.toString()
            password = inptPassword.text.toString()
            Toast.makeText(this@SignOut, "Регистрирую", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Requests.signOut(email, password)
                    runOnUiThread {
                        startActivity(Intent(this@SignOut, SignIn::class.java))
                        finish()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@SignOut, e.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("error regist", e.message.toString())
                }
            }
        }
    }



}