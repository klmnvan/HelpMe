package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityOnBoardBinding
import com.example.matuleme.databinding.ActivitySignInBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.Requests
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SignIn : CustomActivity() {
    private lateinit var binding: ActivitySignInBinding
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
    }

    private fun pressBtns(){
        with(binding){
            btnSignIn.setOnClickListener {
                val email = inptEmail.text.toString()
                if(!email.isEmailValid()) {
                    Toast.makeText(this@SignIn, "Email не валиден", Toast.LENGTH_SHORT).show()
                } else {
                    sendRequest()
                    /*val dialog = FragmentCheckEmail()
                    dialog.show(supportFragmentManager, "kldjsfhsdkl")
                   */

                    /*startActivity(Intent(this@SignIn, Home::class.java))
                    finish()*/
                }
            }
            btnSignOut.setOnClickListener {
                startActivity(Intent(this@SignIn, SignOut::class.java))
                finish()
            }
            btnReset.setOnClickListener {
                startActivity(Intent(this@SignIn, ForgotPassword::class.java))
                finish()
            }
        }
    }

    private fun sendRequest() {
        with(binding){
            email = inptEmail.text.toString()
            password = inptPassword.text.toString()
            Toast.makeText(this@SignIn, "Попытка входа", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Requests.signIn(email, password)
                    runOnUiThread {
                        PrefManager.act = 2
                        startActivity(Intent(this@SignIn, Home::class.java))
                        finish()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@SignIn, e.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("error sign in", e.message.toString())
                }
            }
        }
    }

}