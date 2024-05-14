package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.matuleme.adapters.AdapterProduct
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityFavoritesBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.ProductsStorage

class Favorites : CustomActivity(), AdapterProduct.Listener {
    private lateinit var binding: ActivityFavoritesBinding
    private val adapterProduct = AdapterProduct(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        initProduct()
    }

    private fun initProduct() {
        with(binding) {
            listViewProduct.adapter = adapterProduct
            listViewProduct.layoutManager = GridLayoutManager(this@Favorites, 2)
            adapterProduct.clear()
            ProductsStorage.listProductFav.forEach {
                adapterProduct.addElement(it)
            }
        }
    }

    private fun pressBtns() {
        with(binding){
            btnShop.setOnClickListener {
                startActivity(Intent(this@Favorites, Basket::class.java))
                finish()
            }
            btnOpenHome.setOnClickListener {
                startActivity(Intent(this@Favorites, Home::class.java))
                finish()
            }
            btnOpenProfile.setOnClickListener {

            }
            btnOpenProfile.setOnClickListener {
                btnShop
            }
            btnOpenSideMenu.setOnClickListener {
                startActivity(Intent(this@Favorites, Home::class.java))
                finish()
            }
        }
    }

    override fun openCardProduct(el: ShopModelTest) {
        startActivity(Intent(this@Favorites, CardProduct::class.java))
        finish()
    }

    override fun updateListProduct() {
        initProduct()
    }
}