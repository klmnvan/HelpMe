package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityCardProductBinding
import com.example.matuleme.databinding.ActivityShopBinding
import com.example.matuleme.objects.ProductsStorage

class CardProduct : CustomActivity() {
    private lateinit var binding: ActivityCardProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        initCard()
    }

    private fun initCard() {
        with(binding) {
            with (ProductsStorage){
                txtTittle.text = currentProduct.name
                txtTittle.text = currentProduct.name
                txtDesc.text = currentProduct.description
                txtPrice.text = currentProduct.price.toString()
            }
        }
    }


    private fun pressBtns() {
        with(binding){
            btnAddBasket.setOnClickListener {
                ProductsStorage.listProductBasket.add(ProductsStorage.currentProduct)
            }
            btnOpenBasket.setOnClickListener {
                startActivity(Intent(this@CardProduct, Basket::class.java))
                finish()
            }
            btnOpenFav.setOnClickListener {
                startActivity(Intent(this@CardProduct, Favorites::class.java))
                finish()
            }
            btnOpenSideMenu.setOnClickListener {
                startActivity(Intent(this@CardProduct, Home::class.java))
                finish()
            }
        }
    }

}