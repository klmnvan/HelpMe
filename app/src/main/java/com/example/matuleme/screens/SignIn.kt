package com.example.matuleme.screens

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.databinding.ActivityOnBoardBinding
import com.example.matuleme.databinding.ActivitySignInBinding
import com.example.matuleme.fragments.FragmentCheckEmail

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOpen.setOnClickListener {
            val dialog = FragmentCheckEmail()
            dialog.show(supportFragmentManager, "kldjsfhsdkl")
        }
    }

}