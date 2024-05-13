package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matuleme.adapters.AdapterCategory
import com.example.matuleme.adapters.AdapterProduct
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityHomeBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.ProductsStorage
import com.example.matuleme.objects.UserData


class Home : CustomActivity(), AdapterProduct.Listener, AdapterCategory.Listener {
    private lateinit var binding: ActivityHomeBinding
    private val adapterProduct = AdapterProduct(this)
    private val adapterCategory = AdapterCategory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ProductsStorage.currenCategory = ""
        pressBtns()
        initProduct()
        initCategories()
    }

    private fun initCategories() {
        with(binding) {
            listViewCategory.adapter = adapterCategory
            adapterCategory.clear()
            ProductsStorage.allCategories.forEach {
                adapterCategory.addElement(it)
            }
        }
    }

    private fun initProduct() {
        with(binding) {
            listViewProduct.adapter = adapterProduct
            listViewProduct.layoutManager = GridLayoutManager(this@Home, 2)
            adapterProduct.clear()
            ProductsStorage.listProduct.forEachIndexed() { i, el ->
                if(i < 2){
                    adapterProduct.addElement(el)
                }
            }
        }
    }

    private fun pressBtns() {
        with(binding){
            btnOpenShop.setOnClickListener {
                startActivity(Intent(this@Home, Basket::class.java))
                finish()
            }
            btnShop.setOnClickListener {
                startActivity(Intent(this@Home, Basket::class.java))
                finish()
            }
            btnOpenFav.setOnClickListener {
                startActivity(Intent(this@Home, Favorites::class.java))
                finish()
            }
            btnOpenNot.setOnClickListener {

            }
            btnOpenProfile.setOnClickListener {

            }
            btnOpenSideMenu.setOnClickListener {

            }
            btnOpenFullAction.setOnClickListener {
                startActivity(Intent(this@Home, Popular::class.java))
                finish()
            }
        }
    }

    override fun openCardProduct(el: ShopModelTest) {
        startActivity(Intent(this@Home, CardProduct::class.java))
        finish()
    }

    override fun updateListProduct() {
        initProduct()
    }

    override fun updateCategory() {
        startActivity(Intent(this@Home, Categories::class.java))
        finish()
    }
}