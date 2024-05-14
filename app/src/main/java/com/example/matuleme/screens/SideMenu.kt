package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivitySideMenuBinding
import com.example.matuleme.databinding.ActivitySignInBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.PrefManager

class SideMenu : CustomActivity() {
    private lateinit var binding: ActivitySideMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySideMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
    }

    private fun pressBtns(){
        with(binding){
            btnProfile.setOnClickListener {
                /*startActivity(Intent(this@SideMenu, Home::class.java))
                finish()*/
            }
            btnBasket.setOnClickListener {
                startActivity(Intent(this@SideMenu, Basket::class.java))
                finish()
            }
            btnFav.setOnClickListener {
                startActivity(Intent(this@SideMenu, Favorites::class.java))
                finish()
            }
            btnOrders.setOnClickListener {
                /*startActivity(Intent(this@SideMenu, Home::class.java))
                finish()*/
            }
            btnNot.setOnClickListener {
                /*startActivity(Intent(this@SideMenu, Home::class.java))
                finish()*/
            }
            btnSettings.setOnClickListener {

            }
            btnLogOut.setOnClickListener {
                PrefManager.act = 1
                startActivity(Intent(this@SideMenu, SignIn::class.java))
                finish()
            }
        }
    }
}