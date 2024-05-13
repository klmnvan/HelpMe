package com.example.matuleme.objects

import com.example.matuleme.models.ShopModelTest

object ProductsStorage {
    var listProduct: MutableList<ShopModelTest> = ArrayList()
    var listProductFav: MutableList<ShopModelTest> = ArrayList()
    var listProductPopular: MutableList<ShopModelTest> = ArrayList()
    var listProductBasket: MutableList<ShopModelTest> = ArrayList()
    var currentProduct: ShopModelTest = ShopModelTest()
    var currenCategory: String = "Все"

    val Price: Int
        get() =  listProductBasket.map { it.price!! }.sum()

    val countProductInB: Int
        get() = listProductBasket.distinct().size

    val allCategories: List<String>
        get() = mutableListOf("Все") + listProduct.map { it.category }.distinct()
}