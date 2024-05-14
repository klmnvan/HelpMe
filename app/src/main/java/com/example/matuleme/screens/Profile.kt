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
import com.example.matuleme.databinding.ActivityProfileBinding
import com.example.matuleme.databinding.ActivitySignInBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.UserStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        initValues()
    }

    private fun initValues() {
        with(binding){
            with(UserStorage){
                inptName.setText(profile.name + " " + profile.surname)
                inptEmail.setText(profile.email)
            }
        }
    }

    private fun pressBtns(){
        with(binding){
            btnBack.setOnClickListener {
                startActivity(Intent(this@Profile, Home::class.java))
                finish()
            }
            btnSave.setOnClickListener {
                sendRequest()
            }
            imgUser.setOnClickListener {
                startActivity(Intent(this@Profile, ProfileRedact::class.java))
                finish()
            }
        }
    }

    private fun sendRequest() {
        Toast.makeText(this@Profile, "Сохраняю данные профиля", Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                runOnUiThread {
                    startActivity(Intent(this@Profile, Home::class.java))
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this@Profile, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                Log.d("error save profile", e.message.toString())
            }
        }

    }
}