package com.example.matuleme.screens

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.matuleme.adapters.AdapterProduct
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityShopBinding
import com.example.matuleme.objects.UserData

class Shop : CustomActivity() {
    lateinit var binding: ActivityShopBinding
    private val adapterShop = AdapterProduct(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        initCross()
    }

    private fun initCross() {
        with(binding) {
            listViewProduct.adapter = adapterShop
            listViewProduct.layoutManager = GridLayoutManager(this@Shop, 2)
            UserData.listAllShop.forEach {
                adapterShop.addElement(it)
            }
        }
    }

    private fun initCategory() {
        with(binding) {
            listViewProduct.adapter = adapterShop
            listViewProduct.layoutManager = GridLayoutManager(this@Shop, 2)
            UserData.listAllShop.forEach {
                adapterShop.addElement(it)
            }
        }
    }

    private fun pressBtns(){
        with(binding){
            /*btnSignIn.setOnClickListener {
                val email = inptEmail.text.toString()
                if(!email.isEmailValid()) {
                    Toast.makeText(this@SignIn, "Email не валиден", Toast.LENGTH_SHORT).show()
                } else {
                    val dialog = FragmentCheckEmail()
                    dialog.show(supportFragmentManager, "kldjsfhsdkl")
                    *//*startActivity(Intent(this@SignIn, Home::class.java))
                    finish()*//*
                }
            }*/
        }
    }

}