package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.matuleme.adapters.AdapterCategory
import com.example.matuleme.adapters.AdapterProduct
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityCategoriesBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.ProductsStorage

class Categories : CustomActivity(), AdapterProduct.Listener, AdapterCategory.Listener {
    private lateinit var binding: ActivityCategoriesBinding
    private val adapterProduct = AdapterProduct(this)
    private val adapterCategory = AdapterCategory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategories()
        initProduct()
        pressBtns()
    }

    private fun pressBtns() {
        with(binding){
            btnOpenSideMenu.setOnClickListener {
                startActivity(Intent(this@Categories, Home::class.java))
                finish()
            }
        }
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
            listViewProduct.layoutManager = GridLayoutManager(this@Categories, 2)
            adapterProduct.clear()
            if(ProductsStorage.currenCategory != "Все"){
                ProductsStorage.listProduct.filter { it.category == ProductsStorage.currenCategory }.forEach {
                    adapterProduct.addElement(it)
                }
            }
            else {
                ProductsStorage.listProduct.forEach {
                    adapterProduct.addElement(it)
                }
            }
        }
    }

    override fun updateCategory() {
        initCategories()
        initProduct()
    }

    override fun openCardProduct(el: ShopModelTest) {
        startActivity(Intent(this@Categories, CardProduct::class.java))
        finish()
    }

    override fun updateListProduct() {
        initProduct()
    }

}