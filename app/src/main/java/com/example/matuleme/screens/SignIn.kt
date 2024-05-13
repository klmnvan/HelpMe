package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
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

class SignIn : CustomActivity() {
    private lateinit var binding: ActivitySignInBinding

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
                    val dialog = FragmentCheckEmail()
                    dialog.show(supportFragmentManager, "kldjsfhsdkl")
                    PrefManager.act = 2
                    /*startActivity(Intent(this@SignIn, Home::class.java))
                    finish()*/
                }
            }
        }
    }

}