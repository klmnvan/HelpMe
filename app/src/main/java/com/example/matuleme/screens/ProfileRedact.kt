package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matuleme.databinding.ActivityProfileredactBinding
import com.example.matuleme.objects.UserStorage

class ProfileRedact : AppCompatActivity() {
    private lateinit var binding: ActivityProfileredactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileredactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        initValues()
    }

    private fun initValues() {
        with(binding){
            with(UserStorage){
                txtName.setText(profile.name + " " + profile.surname)
                inptName.setText(profile.name)
                inptSurname.setText(profile.surname)
                inptPhone.setText(profile.phone)
                inptAddress.setText(profile.address)
            }
        }
    }

    private fun pressBtns(){
        with(binding){
            btnBack.setOnClickListener {
                startActivity(Intent(this@ProfileRedact, Profile::class.java))
                finish()
            }
            btnSave.setOnClickListener {
                val name = inptName.text.toString()
                val surname = inptSurname.text.toString()
                val address = inptAddress.text.toString()
                val phone = inptPhone.text.toString()
                with(UserStorage){
                    profile = profile.copy(
                        name = name,
                        surname = surname,
                        address = address,
                        phone = phone,
                    )
                }
                startActivity(Intent(this@ProfileRedact, Profile::class.java))
                finish()
            }
        }
    }
}